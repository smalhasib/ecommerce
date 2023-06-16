import API from "@/redux/utils/axios";
export const getSingleUser = async (id: string) => {
  const res = await API.get(`/user/${id}`);
  return res.data;
};
