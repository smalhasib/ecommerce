import API from "@/utils/axios";
import React from "react";
import { useQuery } from "react-query";
import Loader from "../Loader";
import { AiOutlineHeart } from "react-icons/ai";
import { FaSearchPlus } from "react-icons/fa";
import Link from "next/link";
import { BsCart4 } from "react-icons/bs";
import { useDispatch } from "react-redux";
import { addToCart } from "@/redux/freatures/cartSlice";

type cartData = {
  id: number;
  name: string;
  price: number;
  quantity: number;
  url: string;
};

const AllProducts = () => {
  const dispatch = useDispatch();
  const addNewToCart = (data: cartData) => {
    console.log(data);
    dispatch(addToCart(data));
  };
  const getAllProduct = () => {
    return API.get("/product/");
  };
  const { isLoading, data, isError, error } = useQuery(
    "products",
    getAllProduct,
    {
      refetchOnMount: true,
      refetchOnWindowFocus: "always",
      staleTime: 60000,
      select: (data) => {
        const product = data.data.content;
        return product;
      },
    }
  );
  console.log(data);
  if (isLoading) {
    return <Loader />;
  }
  return (
    <div className="w-full flex flex-col justify-center items-center">
      <div className="w-[75%] ml-40 flex items-center justify-start">
        <h1 className="text-3xl font-bold text-gray-600 mt-10">Products</h1>
      </div>
      <div className="w-[75%] ml-40 grid grid-cols-4 p-4 gap-16">
        {data?.map((demo: cartData) => (
          <div
            key={demo.id}
            className=" flex flex-col items-start p-3 shadow-md group bg-[#F8F6F5] hover:scale-105 hover:ease-in-out hover:delay-500 hover:duration-500"
          >
            <div className="w-full">
              <img
                src={demo.imageUrl}
                alt="products"
                className="w-full h-[200px] rounded-lg"
              />
            </div>
            <div className="w-full flex justify-between items-center p-3">
              <span className="text-sm text-gray-700">{demo.name}</span>
              <span className="text-sm text-gray-700">৳ {demo.price} TK</span>
            </div>
            <div className=" w-full flex justify-center gap-x-8 items-center relative bottom-3 mt-5 p-3">
              <BsCart4
                onClick={() => {
                  addNewToCart(demo);
                }}
                className="text-lg text-gray-700 cursor-pointer"
              />
              <AiOutlineHeart className="text-lg text-gray-700 cursor-pointer" />
              <Link href={`/products/${demo.id}`}>
                <FaSearchPlus className="text-lg text-gray-700" />
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AllProducts;
