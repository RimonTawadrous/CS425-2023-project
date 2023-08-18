import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api/v1",
});

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
});

axiosInstance.interceptors.response.use((response) => {
  // Do something after the response is received.
  if (response.status === 401) {
    console.log("Unauthorized");
    localStorage.removeItem("token");
    window.location.href = "/login";
  }
  return response;
});

export default axiosInstance;
