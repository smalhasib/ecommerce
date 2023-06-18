"use client";
import React from "react";
import { BsArrowRight } from "react-icons/bs";
import { BsCart4 } from "react-icons/bs";
import { AiOutlineHeart } from "react-icons/ai";
import { FaSearchPlus } from "react-icons/fa";
import Link from "next/link";
import { addToCart } from "@/redux/freatures/cartSlice";
import { useDispatch } from "react-redux";

const demoProducts = [
  {
    id: 1,
    name: "T-shirt",
    price: 100,
    quantity: 1,
    descpriton:
      " Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam, provident.",
    url: "https://img.freepik.com/free-photo/black-shirt-with-word-ultra-it_1340-37775.jpg?w=826&t=st=1686961661~exp=1686962261~hmac=7bc051bf55c1ef3a2ed0ac91a213b44cca54c078a0ca456c34c1459ed72f88e6",
  },
  {
    id: 2,
    name: "Gents Pant",
    price: 100,
    quantity: 1,
    descpriton:
      " Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam, provident.",
    url: "https://img.freepik.com/free-photo/jeans-wooden-background_93675-134598.jpg?size=626&ext=jpg&uid=R57972027&ga=GA1.1.1052981044.1675978946&semt=ais",
  },
  {
    id: 3,
    name: "Watch",
    price: 100,
    quantity: 1,
    descpriton:
      " Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam, provident.",
    url: "https://img.freepik.com/free-photo/closeup-shot-golden-watch-isolated_181624-28492.jpg?w=740&t=st=1686961737~exp=1686962337~hmac=a798b92a77826c590eb885630c7dceccc1b1153e500c5d0e1cef8b475ddc9817",
  },
  {
    id: 4,
    name: "Ladies Bag",
    price: 100,
    quantity: 1,
    description:
      " Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsam, provident.",
    url: "https://img.freepik.com/free-photo/pink-handbags_1203-7829.jpg?t=st=1686961757~exp=1686962357~hmac=4d52979466975cd66e677a9d09947890257270b593a3f0b0e96347f903eed436",
  },
];

const Products = () => {
  const dispatch = useDispatch();
  return (
    <React.Fragment>
      <div className="w-full flex justify-center min-h-screen">
        <div className="w-[60%] mt-40 flex flex-col items-start justify-start">
          <h1 className="text-3xl font-bold text-gray-600">Products</h1>

          <div className="w-full grid grid-cols-4 p-4 gap-8">
            {demoProducts.map((demo) => (
              <div
                key={demo.id}
                className="flex flex-col items-start p-3 shadow-md group bg-[#F8F6F5]"
              >
                <div className="w-full">
                  <img
                    src={demo.url}
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
