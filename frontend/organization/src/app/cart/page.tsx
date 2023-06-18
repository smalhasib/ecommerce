"use client";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { MdClear } from "react-icons/md";
import {
  getCartTotal,
  removeItem,
  decreaseItemQuantity,
  increaseItemQuantity,
} from "@/redux/freatures/cartSlice";
import Image from "next/image";

type cartType = {
  id: number;
  name: string;
  price: number;
  quantity: number;
  description: string;
  url: string;
};
const Cart = () => {
  const dispatch = useDispatch();
  const { cart, totalQuantity, totalPrice } = useSelector(
    (state) => state.allCart
  );

  useEffect(() => {
    dispatch(getCartTotal());
  }, [dispatch, cart]);
  console.log(cart);
  return (
    <React.Fragment>
      <div className="w-full h-screen">
        <div className="w-full flex justify-center absolute top-[15%]">
          <div className="w-full fkex justify-center items-end">
            <div className="w-[60%] flex flex-col items-center justify-center p-5">
              {cart?.map((product: cartType) => (
                <div
                  key={product.id}
                  className="w-[400px] flex flex-col items-end border-[1px] shadow-md"
                >
                  {/* <MdClear className="text-lg text-white bg-red-600" /> */}
                  <div className="w-full flex justify-between">
                    <Image
                      src={"/images/logoText.png"}
                      height={200}
                      width={200}
                      alt="img"
                    />
                    <div className="w-full flex flex-col items-center">
                      <h1 className="text-sm text-gray-700 mt-2">{product.name}</h1>
                      <h1 className="text-sm text-gray-700 mt-2"> ৳ {product.price} TK</h1>
                      <div className="flex items-center justify-center gap-x-3 mt-3">
                        <button className="text-lg text-gray-700">-</button>
                        <span className="text-sm text-gray-700">0</span>
                        <button className="text-lg text-gray-700">+</button>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
          <div className="w-full">
            {" "}
            <h1>WOrld</h1>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Cart;
