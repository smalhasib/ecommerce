"use client";
import Image from "next/image";
import Link from "next/link";
import React, { useState } from "react";
import { BiSearchAlt2, BiUser } from "react-icons/bi";
import { BsCart4 } from "react-icons/bs";
import { Poppins } from "@next/font/google";
import styles from "./navbar.module.css";
import { usePathname } from "next/navigation";

const poppins = Poppins({
  weight: "400",
  subsets: ["latin"],
});
const Navbar = () => {
  const [isScroll, setIsScroll] = useState<boolean>(false);
  const pathname = usePathname();
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
            <BiUser className="text-lg text-gray-800 cursor-pointer" />
            <BsCart4 className="text-lg text-gray-800 cursor-pointer" />
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Navbar;
