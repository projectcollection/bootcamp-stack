package com.snva.springboot.bootcamp.controller.v1.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snva.springboot.bootcamp.controller.v1.request.UserSignupRequest;
import com.snva.springboot.bootcamp.controller.v1.request.recruitment.EditApplicantRequest;
import com.snva.springboot.bootcamp.controller.v1.response.LoginResponse;
import com.snva.springboot.bootcamp.controller.v1.response.UploadFileResponse;
import com.snva.springboot.bootcamp.controller.v1.response.ml.api.ResumeParsingResponse;
import com.snva.springboot.bootcamp.dto.model.recruitment.ApplicantDto;
import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import com.snva.springboot.bootcamp.dto.response.Response;
import com.snva.springboot.bootcamp.security.CustomUserDetailsService;
import com.snva.springboot.bootcamp.security.SecurityConstants;
import com.snva.springboot.bootcamp.service.FileStorageService;
import com.snva.springboot.bootcamp.service.IResumeParsingService;
import com.snva.springboot.bootcamp.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(maxAge = 36000, origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/v1/user")

@Api(value = "brs-application", description = "Operations pertaining to user login acnd logout in the BRS application")
public class JwtAuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @Autowired
    private IResumeParsingService resumeParsingService;


    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private  org.modelmapper.ModelMapper modelMapper;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private UserService userService;

//
//    /mybootcamps
//    /myprogress
//    /leaderboard
//    /onlinePractice--> Code --> Compiled bt Judge API

    /**
     * Handles the incoming POST API "/v1/user/signup"
     *
     * @param userSignupRequest
     * @return
     */
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }

    @PostMapping("/registerCandidate")
    public Response registerCandidate(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }


    @GetMapping("/allUsers")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public  ResponseEntity allUsers(){
         return ResponseEntity.ok().body( userService.allUsersSecure());
    }


    /**
     * Register FarmApplicantRequest new user in the database
     *
     * @param userSignupRequest
     * @return
     */
    private UserDto registerUser(UserSignupRequest userSignupRequest, boolean isAdmin) {
        UserDto userDto = new UserDto()
                .setEmail(userSignupRequest.getEmail())
                .setPassword(userSignupRequest.getPassword())
                .setFirstName(userSignupRequest.getFirstName())
                .setLastName(userSignupRequest.getLastName())
                .setMobileNumber(userSignupRequest.getMobileNumber())
                .setProfilePicture("https://i.pinimg.com/736x/65/49/ca/6549cacdca6c392649a70153981bd27d.jpg")
                .setAdmin(isAdmin);
        return userService.signup(userDto);
    }

    @PutMapping(value = "/updateprofile", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public  ResponseEntity updateProfile(@RequestBody UserDto userDto){
        return ResponseEntity.ok( userService.updateProfile(userDto));
    }

    @GetMapping("/apiprofile")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public  ResponseEntity<?> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> list= authentication.getAuthorities();
        String user=(String)authentication.getPrincipal();
        UserDto userDto= new UserDto();
        userDto=  userService.findUserByEmail(user);
        return  ResponseEntity.ok(userDto);
    }

    @RequestMapping(value ="/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAuthToken(@RequestBody @Valid LoginRequest loginRequest)  {
        LoginResponse loginResponse = new LoginResponse();

        try{
            authernticate(loginRequest.getEmail(),loginRequest.getPassword());
        }catch (Exception ee){
            loginResponse.setResponse(ee.getMessage());
            loginResponse.setStatus("error");
            return  ResponseEntity.accepted().body(loginResponse);
        }

        final UserDetails user = userDetailsService.loadUserByUsername(loginRequest.email);
        UserDto userDetail=
        userService.findUserByEmail(loginRequest.email);
        // generate the token
        // return that to the cleint
        String token= "";
        String login = user.getUsername();
        if (login != null && login.length() > 0) {
            Claims claims = Jwts.claims().setSubject(login);
            List<String> roles = new ArrayList<>();
            user.getAuthorities().stream().forEach(authority -> roles.add(authority.getAuthority()));
            claims.put("roles", roles);
            token  = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                    .compact();
            ;
        }
        loginResponse.setResponse(token);
        loginResponse.setUser(userDetail);
        loginResponse.setStatus("success");
//        return ResponseEntity.ok(TOKEN_PREFIX+token);
        return  ResponseEntity.accepted().body(loginResponse);

    }

    private void authernticate(String email, String password) throws  Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException dE) {
            throw new Exception("User Desabled", dE);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("User Desabled", badCredentialsException);
        }

    }


//
//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        ResponseEntity<String> response = null;
//        UploadFileResponse res = new UploadFileResponse();
//        String fileName = "";
//        String fileDownloadUri = "";
//        try {
//            fileName = fileStorageService.storeFile(file);
//            ResumeParsingResponse resumeParsingResponse = resumeParsingService.getResumeParsed(fileStorageService.loadFileAsResource(fileName));
//            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/downloadFile/")
//                    .path(fileName)
//                    .toUriString();
//            ApplicantDto applicantDto = new ApplicantDto();
//            applicantDto.setDegree(resumeParsingResponse.getData().getDegree());
//            applicantDto.setDesignation(resumeParsingResponse.getData().getDesignition());
//            applicantDto.setEmail(resumeParsingResponse.getData().getEmail());
//            applicantDto.setId(null);
//            applicantDto.setTotalExp(resumeParsingResponse.getData().getTotalExp());
//            applicantDto.setPhone(resumeParsingResponse.getData().getPhone());
//            List<String> resumeLinks = new ArrayList<>();
//            resumeLinks.add(fileDownloadUri);
//            applicantDto.setResumeLinks(resumeLinks);
//            applicantDto.setSkills(resumeParsingResponse.getData().getSkills());
//            applicantDto.setUniversity(resumeParsingResponse.getData().getUniversity());
//            applicantDto.setName(resumeParsingResponse.getData().getName());
//            applicantDto = resumeParsingService.addApplicant(applicantDto);
//            res = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize(), "", applicantDto);
//        } catch (Exception err) {
//            res = new UploadFileResponse(err.getMessage(), err.getLocalizedMessage(), "", 0, err.getMessage(), null);
//            System.out.println(err);
//        }
//        return res;
//    }
//
//    @PostMapping("/uploadMultipleFiles")
//    public List<ApplicantDto> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return resumeParsingService.allApplicants();
//    }
//
//    @PostMapping("/editApplicant")
//    public ResponseEntity<ApplicantDto> editApplicant(@RequestBody EditApplicantRequest editApplicantRequest) {
//        return ResponseEntity.ok(resumeParsingService.updateApplicant(editApplicantRequest));
//    }
//
//    @GetMapping("/applicantById")
//    public ResponseEntity<ApplicantDto> showAllApplicantById(@RequestBody String id) {
//        return ResponseEntity.ok(resumeParsingService.applicantById(id));
//    }
//
//    @GetMapping("/showAllApplicants")
//    public List<ApplicantDto> showAllApplicants() {
//        return resumeParsingService.allApplicants();
//    }
//
//    @GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//



    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class LoginRequest {
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String email;
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String password;
    }

}
