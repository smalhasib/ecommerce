import API from "@/redux/utils/axios";

export const GetAllusers = async() =>{
    const response = await API.get("/user/");
    return response.data.content;
}