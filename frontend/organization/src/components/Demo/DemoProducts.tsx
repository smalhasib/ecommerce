import Image from "next/image";
import React from "react";
import { BsArrowRight } from "react-icons/bs";
import { BsCart4 } from "react-icons/bs";
import { AiOutlineHeart } from "react-icons/ai";
import { FaSearchPlus } from "react-icons/fa";

const demoProducts = [
  {
    id: 1,
    url: "https://img.freepik.com/free-photo/black-shirt-with-word-ultra-it_1340-37775.jpg?w=826&t=st=1686961661~exp=1686962261~hmac=7bc051bf55c1ef3a2ed0ac91a213b44cca54c078a0ca456c34c1459ed72f88e6",
  },
  {
    id: 2,
    url: "https://img.freepik.com/free-photo/businessman-wearing-black-pants-with-white-shirt_53876-102237.jpg?w=740&t=st=1686961693~exp=1686962293~hmac=727006849a71afedad1639359acc11ce75e28c86f79bcf1ea929ff7a0aedbff2",
  },
  {
    id: 3,
    url: "https://img.freepik.com/free-photo/closeup-shot-golden-watch-isolated_181624-28492.jpg?w=740&t=st=1686961737~exp=1686962337~hmac=a798b92a77826c590eb885630c7dceccc1b1153e500c5d0e1cef8b475ddc9817",
  },
  {
    id: 4,
    url: "https://img.freepik.com/free-photo/pink-handbags_1203-7829.jpg?t=st=1686961757~exp=1686962357~hmac=4d52979466975cd66e677a9d09947890257270b593a3f0b0e96347f903eed436",
  },
];

const DemoProducts = () => {
  return (
    <React.Fragment>
      <div className="w-full flex flex-col items-center">
        <div className="w-full flex items-center justify-center">
          <h1 className="text-3xl font-bold text-gray-600 mt-10">Products</h1>
        </div>
        <div className="w-[70%] flex justify-end items-center">
          <div className="flex items-center justify-end gap-x-2 cursor-pointer">
            <span className="text-sm text-gray-700 hover:text-black">
              View all
            </span>
            <BsArrowRight className="text-sm text-gray-700" />
          </div>
        </div>

        <div className="w-[70%] grid grid-cols-4 p-4 gap-10">
          {demoProducts.map((demo) => (
            <div key={demo.id} className="h-[300px] p-2 shadow-md group">
              <img src={demo.url} alt="products" className="w-full h-full" />
              <div className="invisible flex justify-center gap-x-3 items-center relative bottom-12 group-hover:visible">
                <BsCart4 />
                <AiOutlineHeart />
                <FaSearchPlus />
              </div>
            </div>
          ))}
        </div>
      </div>
    </React.Fragment>
  );
};

export default DemoProducts;
