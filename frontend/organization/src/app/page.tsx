"use client";
import DemoProducts from "@/components/Demo/DemoProducts";
import Features from "@/components/Hero/Features";
import Hero from "@/components/Hero/Hero";
import React from "react";

const Home = () => {
  return (
    <React.Fragment>
      <div className="w-full flex justify-center items-center">
        <Hero />
        <div className="w-[70%] h-[75vh] flex justify-center items-center absolute top-32 bg-[#00000080]">
          <div className="flex flex-col w-[50%] items-center p-5">
            <h1 className="text-4xl font-bold text-gray-300">
              Find Your Best Products.
            </h1>
            <p className="text-sm p-3 text-gray-400">
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. <br />{" "}
              Atque hic quaerat consectetur numquam.
            </p>
            <button className="py-2 px-5 bg-black text-white font-bold text-sm mt-5 hover:bg-gray-400 hover:text-black">
              SHOP NOW
            </button>
          </div>
        </div>
      </div>
      <Features />
      <DemoProducts />
    </React.Fragment>
  );
};

export default Home;
