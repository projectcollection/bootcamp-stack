package com.snva.springboot.bootcamp.controller.v1.api;

import com.google.gson.Gson;
import com.snva.springboot.bootcamp.controller.v1.request.recruitment.EditApplicantRequest;
import com.snva.springboot.bootcamp.controller.v1.response.UploadFileResponse;
import com.snva.springboot.bootcamp.controller.v1.response.ml.api.ResumeParsingResponse;
import com.snva.springboot.bootcamp.dto.model.recruitment.ApplicantDto;
import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import com.snva.springboot.bootcamp.security.CustomUserDetailsService;
import com.snva.springboot.bootcamp.service.FileStorageService;
import com.snva.springboot.bootcamp.service.IResumeParsingService;
import com.snva.springboot.bootcamp.service.UserService;
import com.snva.springboot.bootcamp.util.EmailUtil;
import dev.sayem.jsontotable.HtmlTable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin(maxAge = 36000, origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/v1/applicant")

@Api(value = "brs-application", description = "Operations pertaining to user login acnd logout in the BRS application")
public class ApplicantController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantController.class);

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

    @PostMapping("/uploadFile")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        ResponseEntity<String> response = null;
        UploadFileResponse res = new UploadFileResponse();
        String fileName = "";
        String fileDownloadUri = "";
        try {
            fileName = fileStorageService.storeFile(file);
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            ResumeParsingResponse resumeParsingResponse = resumeParsingService.getResumeParsed(fileStorageService.loadFileAsResource(fileName));
            ApplicantDto applicantDto = getApplicantDto(fileDownloadUri, resumeParsingResponse);
            applicantDto = resumeParsingService.addApplicant(applicantDto);
            res = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize(), "", applicantDto);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> list= authentication.getAuthorities();
            String user=(String)authentication.getPrincipal();
            UserDto userDto= new UserDto();
            userDto=  userService.findUserByEmail(user);

            EmailUtil.sendEmail( getSession(),userDto.getEmail()+", abhishek.joshi@snva.com, akshay.midha@snva.com","Applicant Namely "+ applicantDto.getName() ==""?"Un Named":applicantDto.getName()
                    +" Imported !", HtmlTable.fromJson(new Gson().toJson( res)));

        } catch (Exception err) {
            res = new UploadFileResponse(err.getMessage(), err.getLocalizedMessage(), "", 0, err.getMessage(), null);
            System.out.println(err);
        }
        return res;
    }



    @PostMapping("/uploadMultipleFiles")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        ResponseEntity<String> response = null;
        List<UploadFileResponse> applicantDtos = new ArrayList<UploadFileResponse>();
        for (MultipartFile file : files) {

            UploadFileResponse res = new UploadFileResponse();
            String fileName = "";
            String fileDownloadUri = "";
            try {
                fileName = fileStorageService.storeFile(file);
                fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(fileName)
                        .toUriString();
                ResumeParsingResponse resumeParsingResponse = resumeParsingService.getResumeParsed(fileStorageService.loadFileAsResource(fileName));
                ApplicantDto applicantDto = getApplicantDto(fileDownloadUri, resumeParsingResponse);
                applicantDto = resumeParsingService.addApplicant(applicantDto);
                res = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize(), "", applicantDto);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                String user=(String)authentication.getPrincipal();
                UserDto userDto= new UserDto();
                userDto=  userService.findUserByEmail(user);
                EmailUtil.sendEmail( getSession(),userDto.getEmail()+",abhishek.joshi@snva.com,akshay.midha@snva.com","Resume Imported", HtmlTable.fromJson(new Gson().toJson( res)));

                applicantDtos.add(res);
            } catch (Exception err) {
                res = new UploadFileResponse(err.getMessage(), err.getLocalizedMessage(), "", 0, err.getMessage(), null);
                System.out.println(err);
            }
        }
        return ResponseEntity.ok(applicantDtos);
    }



    @PostMapping("/editApplicant")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<ApplicantDto> editApplicant(@RequestBody EditApplicantRequest editApplicantRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> list= authentication.getAuthorities();
        String user=(String)authentication.getPrincipal();
        UserDto userDto= new UserDto();
        userDto=  userService.findUserByEmail(user);
        ApplicantDto applicantDto= resumeParsingService.updateApplicant(editApplicantRequest);
        EmailUtil.sendEmail( getSession(),userDto.getEmail()+", abhishek.joshi@snva.com, akshay.midha@snva.com","Applicant Namely "+ applicantDto.getName() ==""?"Un Named":applicantDto.getName()
                +" Imported !", HtmlTable.fromJson(new Gson().toJson( applicantDto)));
        return ResponseEntity.ok(applicantDto);
    }

    @PostMapping("/applicantById")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<ApplicantDto> showAllApplicantById(@RequestBody String id) {
        return ResponseEntity.ok(resumeParsingService.applicantById(id));
    }

    @GetMapping("/showAllApplicants")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public List<ApplicantDto> showAllApplicants() {
        return resumeParsingService.allApplicants();
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private ApplicantDto getApplicantDto(String fileDownloadUri, ResumeParsingResponse resumeParsingResponse) {
        ApplicantDto applicantDto = new ApplicantDto();
        applicantDto.setDegree(resumeParsingResponse.getData().getDegree());
        applicantDto.setDesignation(resumeParsingResponse.getData().getDesignition());
        applicantDto.setEmail(resumeParsingResponse.getData().getEmail());
        applicantDto.setId(null);
        applicantDto.setTotalExp(resumeParsingResponse.getData().getTotalExp());
        applicantDto.setPhone(resumeParsingResponse.getData().getPhone());
        List<String> resumeLinks = new ArrayList<>();
        resumeLinks.add(fileDownloadUri);
        applicantDto.setResumeLinks(resumeLinks);
        applicantDto.setSkills(resumeParsingResponse.getData().getSkills());
        applicantDto.setUniversity(resumeParsingResponse.getData().getUniversity());
        applicantDto.setName(resumeParsingResponse.getData().getName());
        return applicantDto;
    }


    private Session getSession() {
        
        final String fromEmail = "dheeraj.singh@snva.com"; //requires valid gmail id
        final String password = "dheerajthedev@123"; // correct password for gmail id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        return Session.getInstance(props, auth);
    }
}
