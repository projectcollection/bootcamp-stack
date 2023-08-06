import "../style/bootcampstyles.css"

export const SocialMediaSection = (props) => {
    return (
      <>
        <section id="socialMediaChecker" className="p-5 bg-dark text-light">
      <div className="container">
        <div className="row align-items-center justify-content-between">
          <div className="col-md p-5">            
            <p className="lead whiteColor">             
              <h2>Check Your Social Media Profile</h2>
            </p>
            <section>
              <div className="mb-3 row">
                <label for="socialMediaSelect" className="form-label col whiteColor">Social Media Type</label>
                <select className="form-select col" id="socialMediaSelect" aria-label="Choose Social Media ">
                  <option selected>Open this select menu</option>
                  <option value="1">GitHub</option>
                  <option value="2">Linkedin</option>
                  <option value="3">Facebook</option>
                  <option value="4">Tumbler</option>
                  <option value="5">Instagram</option>
                </select>                
              </div>   
              <div className="mb-3 row">
                <label for="userName" className="form-label col whiteColor">Social Media Type</label>
                <input type="text" id="userName" className="form-input col"/>
                
              </div>  

              <div className="mb-3 row">
                  <input id="getProfileBtn" type="button" placeholder="Enter profile" value="Get The Profile" className="btn btn-light mt-3"/>              
              </div>           
            </section>           
            
          </div>
          <div className="col-md">
            <img id="profileImage" className ="profile" src="https://cdn1.vectorstock.com/i/1000x1000/31/95/user-sign-icon-person-symbol-human-avatar-vector-12693195.jpg" className="img-fluid rounded-circle" alt="" />
          </div>
          <div className="col-md">
            <span id="followers" data-followers-url="" className="badge rounded-pill bg-light text-dark"></span>
            <span id="following" className="badge rounded-pill bg-dark"></span>
          </div>
        </div>

        <div className="row align-items-center align-items-center">
          <div className="col-12 whiteColor">
            <h1>User Profile</h1>
          </div>
        </div>


        
        <div id="followersAccounts" className="row align-items-center align-items-center invisible ">
          <div className="accordion accordion-flush bg-light text-dark" id="accordionFlushExample">                    
          </div>
        </div>
      </div>
    </section>
      </>
    );
  };