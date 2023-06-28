"use client";
import React, { useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useMutation } from "react-query";
import API from "@/utils/axios";
import Cookies from "js-cookie";

type productType = {
  name: string;
  description: string;
  price: number;
  url: string;
};

type dataType = {
  name: string;
  description: string;
  price: number;
  url: string;
  quantity: number;
  sellerId: number;
};
const AddProducts = () => {
  const [pic, setPic] = useState();
  const router = useRouter();
  const [product, setProduct] = useState<productType>({
    name: "",
    description: "",
    price: 0,
    url: "",
  });

  const clearInput = () => {
    setProduct({
      name: "",
      description: "",
      price: 0,
      url: "",
    });
  };

  const { isSuccess, mutate } = useMutation(({ data }) => {
    return (
      API.post("/product/create", data),
      {
        onSuccess: () => {
          clearInput();
        },
      }
    );
  });
  const handleChange = (event) => {
    setProduct({ ...product, [event.target.name]: event.target.value });
  };

  const picDetails = async (pics) => {
    if (pics === undefined) {
      alert("Did not match format or others");
      return;
    }
    try {
      if (pics.type === "image/jpeg" || pics.type === "image/png") {
        const data = new FormData();
        data.append("file", pics);
        data.append("upload_preset", "chat-app");
        data.append("cloud_name", "shanto78");
        const imgData = await axios.post(
          "https://api.cloudinary.com/v1_1/shanto78/image/upload",
          data
        );
        console.log(imgData.data.url.toString());
        setPic(imgData.data.url.toString());
      } else {
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleSubmit = async() => {
    const data = {
      name: product.name,
      description: product.description,
      price: product.price,
      quantity: 1,
      url: pic,
      sellerId: Cookies.get("org_user_id"),
    };
    console.log(data);
    const res = await API.post("/product/create", data);
    console.log(res);
    // mutate(data);
  };
  return (
    <React.Fragment>
      <div className="w-full h-screen flex justify-center items-center">
        <div className="w-[40%] flex flex-col items-start justify-start absolute top-[20%]">
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">
              Product name
            </label>
            <input
              type="text"
              name="name"
              value={product.name}
              onChange={handleChange}
              className="w-full px-3 py-2 outline-none border-2 rounded-md mt-1"
            />
          </div>
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">
              Product description
            </label>
            <textarea
              name="description"
              value={product.description}
              onChange={handleChange}
              className="w-full px-3 py-2 outline-none border-2 rounded-md mt-1"
            />
          </div>
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">
              Product price
            </label>
            <input
              type="number"
              name="price"
              value={product.price}
              onChange={handleChange}
              className="w-full px-3 py-2 outline-none border-2 rounded-md mt-1"
            />
          </div>
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">
              Product Image
            </label>
            <input
              type="file"
              name="url"
              value={product.url}
              onChange={(e) => picDetails(e.target.files[0])}
              className="w-full px-3 py-2 outline-none border-2 rounded-md mt-1"
            />
          </div>
          <div className="w-full flex justify-center items-center">
            <button
              onClick={handleSubmit}
              className="mt-5 px-8 py-2 bg-black text-white text-sm"
            >
              Add product
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default AddProducts;
