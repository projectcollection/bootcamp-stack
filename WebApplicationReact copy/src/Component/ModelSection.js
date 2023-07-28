

export const ModelSection = (props) => {
  return (
    <>
      <div
        className="modal fade"
        id="enroll"
        tabindex="-1"
        aria-labelledby="enrollLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="enrollLabel">
                Enrollment
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <p className="lead">
                Fill out this form and we will get back to you
              </p>
              <form>
                <div className="mb-3">
                  <label for="first-name" className="col-form-label">
                    First Name:
                  </label>
                  <input type="text" className="form-control" id="first-name" />
                </div>
                <div className="mb-3">
                  <label for="last-name" className="col-form-label">
                    Last Name:
                  </label>
                  <input type="text" className="form-control" id="last-name" />
                </div>
                <div className="mb-3">
                  <label for="email" className="col-form-label">
                    Email:
                  </label>
                  <input type="email" className="form-control" id="email" />
                </div>
                <div className="mb-3">
                  <label for="phone" className="col-form-label">
                    Phone:
                  </label>
                  <input type="tel" className="form-control" id="phone" />
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button type="button" className="btn btn-primary">
                Submit
              </button>
            </div>
          </div>
        </div>
      </div>

      <div id="contact">
        <div id="decId" className="decclassName">
          Some Data
        </div>
      </div>
    </>
  );
};
