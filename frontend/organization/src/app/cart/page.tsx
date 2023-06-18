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

  const data = cart?.map((crt: { id: number; quantity: number }) => ({
    id: crt.id,
    quantity: crt.quantity,
  }));
  console.log(data);

  const purchase = ()=>{
    // ({data, userid})
  }
  return (
    <React.Fragment>
      <div className="w-full min-h-screen">
        <div className="w-full flex justify-center">
          <div className="w-full flex justify-center items-end mt-32">
            <div className="w-[60%] flex flex-col items-end justify-center p-5">
              {cart?.map((product: cartType) => (
                <div
                  key={product.id}
                  className="w-[400px] flex flex-col items-end border-[1px] border-[#eee] shadow-md mt-5"
                >
                  {/* <MdClear className="text-lg text-white bg-red-600" /> */}
                  <div className="w-full flex justify-between">
                    <img
                      src={product.url}
                      alt="img"
                      className="h-[200px] w-[50%]"
                    />
                    <div className="w-full flex flex-col items-center">
                      <h1 className="text-sm text-gray-700 mt-2">
                        {product.name}
                      </h1>
                      <h1 className="text-sm text-gray-700 mt-2">
                        {" "}
                        ৳ {product.price} TK
                      </h1>
                      <h1 className="text-sm text-gray-700 mt-2">
                        Quantity : {product.quantity}
                      </h1>
                      <div className="flex items-center justify-center gap-x-3 mt-3">
                        <button
                          onClick={() =>
                            dispatch(decreaseItemQuantity(product.id))
                          }
                          className="text-lg text-gray-700"
                        >
                          -
                        </button>
                        <span className="text-sm text-gray-700">
                          {product.quantity}
                        </span>
                        <button
                          onClick={() =>
                            dispatch(increaseItemQuantity(product.id))
                          }
                          className="text-lg text-gray-700"
                        >
                          +
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
          <div className="w-full mt-32">
            <div className="w-full flex justify-center items-start p-5">
              <div className="w-[400px] border-[1px] border-[#eee] shadow-md flex flex-col items-center p-3 mt-5">
                <h1 className="text-sm text-gray-700 mt-2">
                  Quantity : {totalQuantity}
                </h1>
                <h1 className="text-sm text-gray-700 mt-2">
                  Total Price : {totalPrice}
                </h1>
                <button
                  onClick={purchase}
                  className="px-5 py-1 bg-black text-sm text-white mt-4"
                >
                  Purchase
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Cart;
