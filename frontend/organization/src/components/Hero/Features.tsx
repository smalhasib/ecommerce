import React from "react";
import { BiPhoneCall } from "react-icons/bi";
import { CiDeliveryTruck } from "react-icons/ci";
import { GiReturnArrow } from "react-icons/gi";
import { BsPaypal } from "react-icons/bs";
const Features = () => {
  return (
    <div className="w-full flex justify-center items-center">
      <div className="w-[70%] grid grid-cols-4 bg-[#FAFAFA] shadow-sm">
        <div className="flex flex-col items-center p-4 border-[1px]">
          <div className="flex items-center justify-start gap-x-2">
            <BiPhoneCall className="text-3xl" />
            <span className="text-md text-gray-600">Call us Anytime</span>
          </div>
          <span className="text-sm text-gray-600 ml-5">+8801785303538</span>
        </div>
        <div className="flex flex-col items-center p-4 border-[1px]">
          <div className="flex items-center justify-start gap-x-2">
            <CiDeliveryTruck className="text-3xl" />
            <span className="text-md text-gray-600">Free Shipping</span>
          </div>
          <span className="text-sm text-gray-600 ml-5">
            When you spend $100+
          </span>
        </div>
        <div className="flex flex-col items-center p-4 border-[1px]">
          <div className="flex items-center justify-start gap-x-2">
            <GiReturnArrow className="text-3xl" />
            <span className="text-md text-gray-600">Free Returns</span>
          </div>
          <span className="text-sm text-gray-600 ml-5">
            30-days free return policy.
          </span>
        </div>
        <div className="flex flex-col items-center p-4 border-[1px]">
          <div className="flex items-center justify-start gap-x-2">
            <BsPaypal className="text-3xl" />
            <span className="text-md text-gray-600">Secure payment</span>
          </div>
          <span className="text-sm text-gray-600 ml-5">
            We ensure secure payment.
          </span>
        </div>
      </div>
    </div>
  );
};

export default Features;
