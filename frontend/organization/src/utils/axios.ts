import axios from "axios";
import Cookies from "js-cookie";
const BASE_URL = "http://localhost:8090/api";
const API = axios.create({ baseURL: BASE_URL });

API.interceptors.request.use(async (req) => {
  const token = Cookies.get("org_accessToken");
  if (token) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
});

export default API;
