package com.snva.springboot.bootcamp;

import com.snva.springboot.bootcamp.config.FileStorageProperties;
import com.snva.springboot.bootcamp.model.bootcamp.*;
import com.snva.springboot.bootcamp.model.bus.*;
import com.snva.springboot.bootcamp.model.user.*;
import com.snva.springboot.bootcamp.repository.bus.*;
import com.snva.springboot.bootcamp.repository.bootcamp.BootcampRepository;
import com.snva.springboot.bootcamp.repository.bootcamp.SessionItemRepository;
import com.snva.springboot.bootcamp.repository.bootcamp.SessionRepository;
import com.snva.springboot.bootcamp.repository.bootcamp.TechnologyRepository;

import com.snva.springboot.bootcamp.repository.user.ProfileRepository;
import com.snva.springboot.bootcamp.repository.user.RoleRepository;
import com.snva.springboot.bootcamp.repository.user.UserRepository;
import com.snva.springboot.bootcamp.util.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ApplicantProgressTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicantProgressTrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProfileRepository profileRepository, BootcampRepository bootcampRepository,
                           SessionRepository sessionRepository, TechnologyRepository technologyRepository,
                           RoleRepository roleRepository, UserRepository userRepository,
                           StopRepository stopRepository, AgencyRepository agencyRepository,
                           BusRepository busRepository, TripRepository tripRepository, SessionItemRepository sessionItemRepository,
                           TripScheduleRepository tripScheduleRepository) {
        return args -> {
            if (false) {
                System.out.println("Bootcamp Creation  !!!!");
                List<User> user = new ArrayList<>();
                List<Technology> technologyList = technologyRepository.findByName("React Js");
                Technology springboot = new Technology();
                Technology reactjs = new Technology();
                if (technologyList.size() < 1) {
                    springboot.setName("React Js");
                    springboot.setVersion("18x");
                    springboot.setVendorName("Meta");
                    springboot = technologyRepository.save(springboot);
                    System.out.println("Technology Create Successfully !!!!");

                    reactjs = new Technology();
                    reactjs.setName("Spring Framework");
                    reactjs.setVersion("3x");
                    reactjs.setVendorName("Spring People");
                    reactjs = technologyRepository.save(reactjs);
                    technologyList.add(springboot);
                    technologyList.add(reactjs);
                }

                List<SessionItem> sessionItems = new ArrayList<>();

                SessionItem sessionItem1 = new SessionItem();
                sessionItem1.setName("Item1");
                sessionItem1.setDetails("This is the item 1");
                sessionItem1.setSessionLink("www.xxx1.com");
                sessionItem1.setSessionType(SessionType.PDF);
                sessionItem1.setSessionContentRecorded("Webex Recording Link");
                sessionItem1= sessionItemRepository.save(sessionItem1);


                SessionItem sessionItem2 = new SessionItem();
                sessionItem2.setName("Item2");
                sessionItem2.setDetails("This is the item 2");
                sessionItem2.setSessionLink("www.xxx2.com");
                sessionItem2.setSessionType(SessionType.CODE);
                sessionItem2.setSessionContentRecorded("Webex Recording Link2");
                sessionItem2= sessionItemRepository.save(sessionItem2);
                sessionItems.add(sessionItem1);
                sessionItems.add(sessionItem2);

                List<Session> sessionList = sessionRepository.findByName("Item1");
                if (sessionList.size() < 1) {
                    Session session1 = new Session();
                    session1.setName("Item1");
                    session1.setSessionDate(new Date());
                    session1.setSessionItems(sessionItems);
                    session1=sessionRepository.save(session1);
                    sessionList.add(session1);
                    System.out.println("Session Create Successfully !!!!");
                }

                List<Bootcamp> bootcamps = bootcampRepository.findByName("Full Stack Java");
                if (bootcamps.size() < 1) {
                    // we need to make one as it is zero
                    Bootcamp bootcamp = new Bootcamp();
                    bootcamp.setDescription("some desc")
                            .setEndDate(new Date())
                            .setName("Full Stack Java")
                            .setLongHtml("some long html")
                            .setStartDate(new Date())
                            .setTechnologyStack(technologyList)
                            .setUsers(user)
                            .setSessions(sessionList);
                    bootcampRepository.save(bootcamp);
                    System.out.println("Bootcamp Create Successfully !!!!");
                }
            }

            if(false){

                Role participantRole = roleRepository.findByRole("PARTICIPANT");
                if (participantRole == null) {
                    participantRole = new Role();
                    participantRole.setRole("PARTICIPANT");
                    roleRepository.save(participantRole);
                }
                HashSet<Role> roles = new HashSet<>();
                roles.add(participantRole);
                   User user = new User();
                   HashSet<UserProfile> userProfiles =new HashSet<>();

                   UserProfile userProfile = new UserProfile();
                   userProfile.setName("dheeraj.singh@snva.com");


                HashSet<Experience> experiences = new HashSet<>();

                experiences.add(new Experience()
                        .setName("Software Developer Intern")
                        .setDescription("As FarmApplicantRequest FarmApplicantRequest developer i used to wbdkfkasjfkasfgsdjfklg")
                        .setYears(1.5f));

                HashSet<Education> educations = new HashSet<>();
                educations.add(
                        new Education()
                                .setName("HighSchool")
                                .setCity("Some city")
                                .setCountry("USA")
                                .setUniversity("Some University")
                                .setState("Some state")
                );

                HashSet<Skill> skills = new HashSet<>();
                skills.add(
                        new Skill()
                                .setSkillName("Java")
                                .setProficieny(5)
                                .setCertificationLink("some cert links")


                );
                HashSet<Project> projects= new HashSet<>();
                projects.add(
                        new Project()
                                .setName("The Apirio")
                                .setRoles("I was just FarmApplicantRequest beginner i used to manage the docs")
                                .setTeamSize(5)
                                .setDetails("some lengthy details")
                                .setResponsibilities("Some responsibilities")
                                .setTechStack("MERN Stack")

                );

                userProfile.setSkills(skills);
                userProfile.setEducation(educations);
                userProfile.setProjects(projects);
                userProfile.setExperience(experiences);


                userProfile= profileRepository.save(userProfile);

                System.out.println("The Profile was created !!!!!!!!!!!!!");

                   user.setFirstName("Dheeraj");
                    user.setLastName("Singh");
                    user.setEmail("dheeraj.singh@snva.com");
                    user.setRoles(roles);
                    user.setMobileNumber("+1 111 11111");
                    user.setAddress("SOme Address USA ");
                    user.setPassword(new BCryptPasswordEncoder().encode("123456")) ;// "123456";
                    user.setProfilePicture("");
                    userProfiles.add(userProfile);
                    user.setUserProfiles(userProfiles);

                System.out.println("The User was created !!!!!!!!!!!!!"+ userRepository.save(user));
            }

            if (false){
                Role participantRole = roleRepository.findByRole("PARTICIPANT");
                if (participantRole == null) {
                    participantRole = new Role();
                    participantRole.setRole("PARTICIPANT");
                    roleRepository.save(participantRole);
                }
                User admin = userRepository.findByEmail("dheeraj1@gmail.com");
                if (admin == null) {

                    Set<UserProfile> userProfiles = new HashSet<>();
                    UserProfile userProfile = new UserProfile("dheeraj1@gmail.com");


                    Experience experience = new Experience();
                    experience.setYears(2);
                    experience.setName("Developer Experience");

                    Project project = new Project();
                    project.setResponsibilities("team lead developing prodctus");
                    project.setTechStack("SPRING BOOT REACT");
                    project.setRoles("Lead");
                    project.setResponsibilities("fjsfdlkjgfsldkjglksjfdg");
                    project.setDetails("some Details");


                    Education education = new Education();
                    education.setCity("education city");
                    education.setCountry("education country");
                    education.setName("Greaducation");
                    education.setUniversity("Some University");


                Set<Education> educations = new HashSet<>();
                Set<Experience> experiences = new HashSet<>();
                Set<Project> projects = new HashSet<>();

                educations.add(education);
                experiences.add(experience);
                projects.add(project);

                    Optional<UserProfile> userProfileOptional= profileRepository.findByName("dheeraj1@gmail.com"+"p1");
                    if (!userProfileOptional.isPresent()){
                         userProfile = new UserProfile("dheeraj1@gmail.com");
                        userProfile.setStackOverflow("Stack");
                        userProfile.setExperience(experiences);
                        userProfile.setGithub("github");
                        userProfile.setProjects(projects);
                        userProfile.setLinkedIn("Linked in ");
                        userProfile.setName("dheeraj1@gmail.com"+"p1");
                        userProfile.setEducation(educations);
                        userProfile = profileRepository.save(userProfile);
                    }
                    userProfiles.add(userProfile);
                    admin = new User()
                            .setEmail("dheeraj1@gmail.com")
                            .setPassword(new BCryptPasswordEncoder().encode("123456")) // "123456"
                            .setFirstName("Dheeraj")
                            .setLastName("Singh")
                            .setMobileNumber("123456789")
                            .setProfilePicture("https://avatars.githubusercontent.com/u/32265439?v=4")
                            .setUserProfiles(userProfiles)
                            .setRoles(new HashSet<>(Arrays.asList(participantRole)));
                    userRepository.save(admin);
                }




            } if(false) {


                //Create Admin and Passenger Roles
                Role adminRole = roleRepository.findByRole("ADMIN");
                if (adminRole == null) {
                    adminRole = new Role();
                    adminRole.setRole("ADMIN");
                    roleRepository.save(adminRole);
                }



                Role userRole = roleRepository.findByRole("PASSENGER");
                if (userRole == null) {
                    userRole = new Role();
                    userRole.setRole("PASSENGER");
                    roleRepository.save(userRole);
                }

                //Create an Admin user
                User admin = userRepository.findByEmail("admin.agencya@gmail.com");
                if (admin == null) {
                    admin = new User()
                            .setEmail("admin.agencya@gmail.com")
                            .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                            .setFirstName("John")
                            .setLastName("Doe")
                            .setMobileNumber("9425094250")
                            .setRoles(new HashSet<>(Arrays.asList(adminRole)));
                    userRepository.save(admin);
                }

                //Create four stops
                Stop stopA = stopRepository.findByCode("STPA");
                if (stopA == null) {
                    stopA = new Stop()
                            .setName("Stop A")
                            .setDetail("Near hills")
                            .setCode("STPA");
                    stopRepository.save(stopA);
                }

                Stop stopB = stopRepository.findByCode("STPB");
                if (stopB == null) {
                    stopB = new Stop()
                            .setName("Stop B")
                            .setDetail("Near river")
                            .setCode("STPB");
                    stopRepository.save(stopB);
                }

                Stop stopC = stopRepository.findByCode("STPC");
                if (stopC == null) {
                    stopC = new Stop()
                            .setName("Stop C")
                            .setDetail("Near desert")
                            .setCode("STPC");
                    stopRepository.save(stopC);
                }

                Stop stopD = stopRepository.findByCode("STPD");
                if (stopD == null) {
                    stopD = new Stop()
                            .setName("Stop D")
                            .setDetail("Near lake")
                            .setCode("STPD");
                    stopRepository.save(stopD);
                }

                //Create an Agency
                Agency agencyA = agencyRepository.findByCode("AGENCYA");
                if (agencyA == null) {
                    agencyA = new Agency()
                            .setName("Green Mile Agency")
                            .setCode("AGENCYA")
                            .setDetails("Reaching desitnations with ease")
                            .setOwner(admin);
                    agencyRepository.save(agencyA);
                }

                //Create FarmApplicantRequest bus
                Bus busA = busRepository.findByCode("AGENCYA-1");
                if (busA == null) {
                    busA = new Bus()
                            .setCode("AGENCYA-1")
                            .setAgency(agencyA)
                            .setCapacity(60);
                    busRepository.save(busA);
                }

                //Add busA to set of buses owned by Agency 'AGENCYA'
                if (agencyA.getBuses() == null) {
                    Set<Bus> buses = new HashSet<>();
                    agencyA.setBuses(buses);
                    agencyA.getBuses().add(busA);
                    agencyRepository.save(agencyA);
                }

                //Create FarmApplicantRequest Trip
                Trip trip = tripRepository.findBySourceStopAndDestStopAndBus(stopA, stopB, busA);
                if (trip == null) {
                    trip = new Trip()
                            .setSourceStop(stopA)
                            .setDestStop(stopB)
                            .setBus(busA)
                            .setAgency(agencyA)
                            .setFare(100)
                            .setJourneyTime(60);
                    tripRepository.save(trip);
                }

                //Create FarmApplicantRequest trip schedule
                TripSchedule tripSchedule = tripScheduleRepository.findByTripDetailAndTripDate(trip, DateUtils.todayStr());
                if (tripSchedule == null) {
                    tripSchedule = new TripSchedule()
                            .setTripDetail(trip)
                            .setTripDate(DateUtils.todayStr())
                            .setAvailableSeats(trip.getBus().getCapacity());
                    tripScheduleRepository.save(tripSchedule);
                }
            }
        };
    }
}
