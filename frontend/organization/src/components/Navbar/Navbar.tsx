"use client";
import Image from "next/image";
import Link from "next/link";
import React, { useEffect, useState } from "react";
import { BiSearchAlt2, BiUser } from "react-icons/bi";
import { BsCart4 } from "react-icons/bs";
import { Poppins } from "@next/font/google";
import styles from "./navbar.module.css";
import { usePathname, useRouter } from "next/navigation";
import Modal from "../Modal/Modal";
import Signin from "../Auth/Signin";
import Signup from "../Auth/Signup";
import { useDispatch, useSelector } from "react-redux";
import { getCartTotal } from "@/redux/freatures/cartSlice";
import Otp from "../Auth/Otp";
import Cookies from "js-cookie";
import { BiUserCircle, BiLogOut } from "react-icons/bi";

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
  const [showOtp, setShowOtp] = useState<boolean>(false);
  const [navbarColor, setNavbarColor] = useState<string>("");
  const [drpdown, setDrpDown] = useState<boolean>(false);
  const [id, setId] = useState<string>("");
  const pathname = usePathname();
  const router = useRouter();

  let token = Cookies.get("org_accessToken");
  let role = Cookies.get("user_role");

  const handleUser = () => {
    if (token) {
      setDrpDown(!drpdown);
    } else {
      setShowLogin(true);
    }
  };

  const profileShow = () => {
    setDrpDown(false);
    if (role === "USER") {
      router.push("/profile/user");
    } else {
      router.push("/profile/supplier");
    }
  };

  const logOut = () => {
    setDrpDown(false);
    Cookies.remove("org_accessToken");
    Cookies.remove("user_role");
    Cookies.remove("org_user_id");
    router.push("/");
  };
  useEffect(() => {
    dispatch(getCartTotal());
  }, [cart, dispatch]);

  // height change function for scrolling..........
  useEffect(() => {
    const handleScroll = () => {
      const scrollPosition = window.scrollY;
      if (scrollPosition > 150) {
        setNavbarColor("shadow-md");
      } else {
        setNavbarColor("");
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <React.Fragment>
      {showLogin && (
        <Modal>
          <Signin setShowLogin={setShowLogin} setShowReg={setShowReg} />
        </Modal>
      )}
      {showReg && (
        <Modal>
          <Signup
            setShowReg={setShowReg}
            setShowLogin={setShowLogin}
            setShowOtp={setShowOtp}
            setId={setId}
          />
        </Modal>
      )}
      {showOtp && (
        <Modal>
          <Otp setShowOtp={setShowOtp} setShowLogin={setShowLogin} id={id} />
        </Modal>
      )}
      <div
        className={`w-full flex justify-center items-center fixed top-0 z-50 bg-white ${
          pathname !== "/" && "shadow-md"
        } ${navbarColor}`}
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
            {/* <li
              className={`${styles.nav_link} ${
                pathname == "/addproducts" && styles.nav_link_active
              }`}
            >
              <Link href="/addproducts">Add products</Link>
            </li> */}
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
            <div className="flex flex-col">
              <BiUser
                onClick={handleUser}
                className="text-lg text-gray-800 cursor-pointer"
              />
              {drpdown && (
                <ul className="absolute top-20 bg-[#eee] p-2 rounded-md">
                  <li
                    onClick={profileShow}
                    className="flex items-center gap-x-3 border-b-[1px] border-gray-900 cursor-pointer p-1"
                  >
                    <BiUserCircle />
                    <span>Profile</span>
                  </li>
                  <li
                    onClick={logOut}
                    className="flex items-center gap-x-3 cursor-pointer p-1"
                  >
                    <BiLogOut />
                    <span>Logout</span>
                  </li>
                </ul>
              )}
            </div>
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
