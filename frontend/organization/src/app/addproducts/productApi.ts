import API from "@/utils/axios";
type productType = {
  name: string;
  description: string;
  price: number;
  url: string;
};
export const AddNewProduct = async (data: productType) => {
  const res = API.post("/product/create", data);
  return res;
};
