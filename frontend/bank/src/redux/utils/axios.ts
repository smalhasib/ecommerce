import axios from "axios";
import Cookies from "js-cookie";
const BASE_URL = "http://localhost:8080/api";
const API = axios.create({ baseURL: BASE_URL });

API.interceptors.request.use(async (req) => {
  // console.log({ BASE_URL });

  const token = Cookies.get("accessToken");
  console.log(token);
  if (token) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
});
export const GetAllusers = async () => {
  const res = await API.get("/user/");
  console.log(res);
};
