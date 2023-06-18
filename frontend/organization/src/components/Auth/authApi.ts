import axios from "axios";
const url: string = "http://localhost:8090/api/auth";
const API = axios.create({ baseURL: url });

export const userLogin = async (data: object) => {
  const res = await API.post("/login", data);
  return res;
};

export const userRegister = async (data: object) => {
  const res = await API.post("/register", data);
  return res;
};

export const userVerify = async (data: object) => {
  const res = await API.post("/verify", data);
  return res;
};
