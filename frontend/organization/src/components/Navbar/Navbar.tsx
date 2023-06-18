"use client";
import Image from "next/image";
import Link from "next/link";
import React, { useEffect, useState } from "react";
import { BiSearchAlt2, BiUser } from "react-icons/bi";
import { BsCart4 } from "react-icons/bs";
import { Poppins } from "@next/font/google";
import styles from "./navbar.module.css";
import { usePathname } from "next/navigation";
import Modal from "../Modal/Modal";
import Signin from "../Auth/Signin";
import Signup from "../Auth/Signup";
import { useDispatch, useSelector } from "react-redux";
import { getCartTotal } from "@/redux/freatures/cartSlice";

const poppins = Poppins({
  weight: "400",
  subsets: ["latin"],
});
const Navbar = () => {
  const dispatch = useDispatch();
  const { cart, totalQuantity } = useSelector((state) => state.allCart);
  const [showLogin, setShowLogin] = useState<boolean>(false);
  const [showReg, setShowReg] = useState<boolean>(false);
  const [isScroll, setIsScroll] = useState<boolean>(false);
  const pathname = usePathname();

  useEffect(() => {
    dispatch(getCartTotal());
  }, [cart, dispatch]);

  // height change function for scrolling..........
  const changeHeight = () => {
    if (window.scrollY >= 150) {
      setIsScroll(true);
    } else {
      setIsScroll(false);
    }
  };
  window.addEventListener("scroll", changeHeight);

  return (
    <React.Fragment>
      {showLogin && (
        <Modal>
          <Signin setShowLogin={setShowLogin} setShowReg={setShowReg} />
        </Modal>
      )}
      {showReg && (
        <Modal>
          <Signup setShowReg={setShowReg} setShowLogin={setShowLogin} />
        </Modal>
      )}
      <div
        className={`w-full flex justify-center items-center fixed z-50 ${
          isScroll ? "bg-white shadow-md transition-all" : "bg-white"
        }`}
      >
        <div
          className={`w-[70%] flex justify-between items-center p-4 ${poppins.className}`}
        >
          <div className="flex items-center justify-start">
            <Image
              src={"/icons/logo.gif"}
              height={70}
              width={80}
              alt="Logo_img"
            />
            <Image
              src={"/images/logoText.png"}
              height={100}
              width={150}
              alt="Logo_img"
            />
          </div>
          <ul className="flex items-center gap-x-8 text-sm text-gray-700">
            <li
              className={`${styles.nav_link} ${
                pathname == "/" && styles.nav_link_active
              }`}
            >
              <Link href="/">Home</Link>
            </li>
            <li
              className={`${styles.nav_link} ${
                pathname == "/products" && styles.nav_link_active
              }`}
            >
              <Link href="/products">Products</Link>
            </li>
            <li
              className={`${styles.nav_link} ${
                pathname == "/blogs" && styles.nav_link_active
              }`}
            >
              <Link href="/blogs">Blogs</Link>
            </li>
            <li
              className={`${styles.nav_link} ${
                pathname == "/about" && styles.nav_link_active
              }`}
            >
              <Link href="/about">About</Link>
            </li>
            <li
              className={`${styles.nav_link} ${
                pathname == "/contact" && styles.nav_link_active
              }`}
            >
              <Link href="/contact">Contact us</Link>
            </li>
          </ul>
          <div className="flex items-center gap-x-5">
            <BiSearchAlt2 className="text-lg text-gray-800 cursor-pointer" />
            <BiUser
              onClick={() => setShowLogin(true)}
              className="text-lg text-gray-800 cursor-pointer"
            />
            <Link href={"/cart"} className="flex">
              <BsCart4 className="text-lg text-gray-800 cursor-pointer" />
              <span className="text-xs relative bottom-2">{totalQuantity}</span>
            </Link>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Navbar;
