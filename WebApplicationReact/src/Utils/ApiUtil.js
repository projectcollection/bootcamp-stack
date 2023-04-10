const AUTH_SERVICE = "http://localhost:8077";
const CHAT_SERVICE = "http://localhost:8081";

const request = (options) => {
  const headers = new Headers();

  if (options.setContentType !== false) {
    headers.append("Content-Type", "application/json");
  }

  if (localStorage.getItem("accessToken")) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem("accessToken")
    );
  }

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

console.log("General Options For Request"+ JSON.stringify(options))

  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export function login(loginRequest) {
  // url: AUTH_SERVICE + "/signin",
  return request({
    url: AUTH_SERVICE + "/api/v1/user/authenticate",
    method: "POST",
    body: JSON.stringify(loginRequest),
  });
}


export function findBootcamps(loginRequest) {
  // url: AUTH_SERVICE + "/signin",
  return request({
    url: AUTH_SERVICE + "/api/v1/bootcamp/getbootcampbyuser",
    method: "POST",
    body: JSON.stringify(loginRequest),
  });
}
export function facebookLogin(facebookLoginRequest) {
  return request({
    url: AUTH_SERVICE + "/facebook/signin",
    method: "POST",
    body: JSON.stringify(facebookLoginRequest),
  });
}

export function signup(signupRequest) {
  return request({
    url: AUTH_SERVICE + "/users",
    method: "POST",
    body: JSON.stringify(signupRequest),
  });
}

export function getCurrentUser() {
  if (!localStorage.getItem("accessToken")) {
    return Promise.reject("No access token set.");
  }

  const headers = new Headers();
  if (localStorage.getItem("accessToken")) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem("accessToken")
    );
  }

  console.log("Worked---------------->")

  return request(
    
    {
    
    // url: AUTH_SERVICE + "/users/me",    
    // method: "GET",
    url: AUTH_SERVICE + "/api/v1/user/apiprofile",
    method: "POST",
    headers: headers    
  });
}

export function getUsers() {
  if (!localStorage.getItem("accessToken")) {
    return Promise.reject("No access token set.");
  }
  const headers = new Headers();
  if (localStorage.getItem("accessToken")) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem("accessToken")
    );
  }

  console.log("Reques to users "+ {
    // url: AUTH_SERVICE + "/users/summaries",
    url: AUTH_SERVICE + "/api/v1/user/allUsers",
    method: "POST",    
    headers: headers
  });

  return request({
    // url: AUTH_SERVICE + "/users/summaries",
    url: AUTH_SERVICE + "/api/v1/user/allUsers",
    method: "GET",    
    headers: headers
  });
}

export function countNewMessages(senderId, recipientId) {
  if (!localStorage.getItem("accessToken")) {
    return Promise.reject("No access token set.");
  }

  return request({
    url: CHAT_SERVICE + "/messages/" + senderId + "/" + recipientId + "/count",
    method: "GET",
  });
}

export function findChatMessages(senderId, recipientId) {
  if (!localStorage.getItem("accessToken")) {
    return Promise.reject("No access token set.");
  }

  return request({
    url: CHAT_SERVICE + "/messages/" + senderId + "/" + recipientId,
    method: "GET",
  });
}

export function findChatMessage(id) {
  if (!localStorage.getItem("accessToken")) {
    return Promise.reject("No access token set.");
  }

  return request({
    url: CHAT_SERVICE + "/messages/" + id,
    method: "GET",
  });
}
