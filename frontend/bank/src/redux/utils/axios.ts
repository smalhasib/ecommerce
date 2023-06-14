import axios from "axios";
import Cookies from "js-cookie";
const BASE_URL = "http://localhost:8080/api";
const API = axios.create({ baseURL: BASE_URL });

API.interceptors.request.use(async (req) => {

  const token = Cookies.get("accessToken");
  if (token) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
});

export default API;
// export const GetAllusers = async () => {
//   console.log("Users getting called")
//   const res = await API.get("/user/");
//   console.log(res.data.content);
// };
