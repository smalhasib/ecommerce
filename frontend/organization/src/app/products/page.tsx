"use client";
import React from "react";
import { BsArrowRight } from "react-icons/bs";
import { BsCart4 } from "react-icons/bs";
import { AiOutlineHeart } from "react-icons/ai";
import { FaSearchPlus } from "react-icons/fa";
import Link from "next/link";
import { addToCart } from "@/redux/freatures/cartSlice";
import { useDispatch } from "react-redux";
import Loader from "@/components/Loader";
import API from "@/utils/axios";
import { useQuery } from "react-query";

const Products = () => {
  const dispatch = useDispatch();
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
    <React.Fragment>
      <div className="w-full flex justify-center min-h-screen">
        <div className="w-[70%] mt-40 flex flex-col items-start justify-start">
          <h1 className="text-3xl font-bold text-gray-600">Products</h1>

          <div className="w-full grid grid-cols-4 p-4 gap-10">
            {data?.map((demo) => (
              <div
                key={demo.id}
                className="flex flex-col items-start p-3 shadow-md group bg-[#F8F6F5] hover:scale-105 hover:ease-in-out hover:delay-500 hover:duration-500"
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
                  <span className="text-sm text-gray-700">
                    ৳ {demo.price} TK
                  </span>
                </div>
                <div className=" w-full flex justify-center gap-x-8 items-center relative bottom-3 mt-5 p-3">
                  <BsCart4
                    onClick={() => {
                      dispatch(addToCart(demo));
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
      </div>
    </React.Fragment>
  );
};

export default Products;
