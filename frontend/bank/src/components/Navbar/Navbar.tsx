"use client";
import Image from "next/image";
import Link from "next/link";
import Cookies from "js-cookie";
import React, { useEffect, useState } from "react";
import navitems from "./navitems";
import styles from "./Navbar.module.css";
import Modal from "../Modal/Modal";
import Login from "../Auth/Login";
import Register from "../Auth/Register";
import Otp from "../Auth/Otp";
import Logout from "../Auth/Logout";
import { useParams, usePathname } from "next/navigation";

const Navbar = () => {
  const [showLogin, setshowLogin] = useState<boolean>(false);
  const [showReg, setshowReg] = useState<boolean>(false);
  const [showOtp, setShowOtp] = useState<boolean>(false);
  const [id, setId] = useState<string>("");
  const [logout, setLogout] = useState<boolean>(false);
  const pathName = usePathname();
  var token = Cookies.get("accessToken");

  return (
    <React.Fragment>
      {showLogin && (
        <Modal setshowLogin={setshowLogin}>
          <Login setshowLogin={setshowLogin} setshowReg={setshowReg} />
        </Modal>
      )}

      {showReg && (
        <Modal setshowReg={setshowReg}>
          <Register
            setshowReg={setshowReg}
            setshowLogin={setshowLogin}
            setId={setId}
            setShowOtp={setShowOtp}
          />
        </Modal>
      )}
      {showOtp && (
        <Modal setshowReg={setshowReg}>
          <Otp setShowOtp={setShowOtp} setshowLogin={setshowLogin} id={id} />
        </Modal>
      )}
      {logout && (
        <Modal setLogout={setLogout}>
          <Logout setLogout={setLogout} />
        </Modal>
      )}
      <div className="w-full flex justify-center items-center shadow-md">
        <div className="w-[70%] flex justify-between item-center p-4">
          <div className="flex justify-start items-center gap-x-3">
            <Image
              src={"/icons/logo.png"}
              height={30}
              width={30}
              alt="Logo_of_bank"
            />
            <span className={`${styles.logo_text} text-gray-700 font-bold`}>
              Ecommerce-Bank
            </span>
          </div>
          <ul className="flex justify-start items-end gap-x-12">
            {navitems.map((item) => (
              <li key={item.id}>
                <Link
                  href={`${item.url}`}
                  className={`${styles.nav_link} ${
                    pathName == item.url && styles.nav_link_active
                  } text-md text-gray-700 font-bold`}
                  onClick={() => {
                    {
                      item.title == "Signin" && setshowLogin(true);
                    }
                    {
                      item.title == "Signup" && setshowReg(true);
                    }
                  }}
                >
                  {item.title}
                </Link>
              </li>
            ))}
            {token ? (
              <ul className="flex justify-start items-end gap-x-10">
                {" "}
                <Link
                  href="/profile"
                  className={`${styles.nav_link} ${
                    pathName == "/profile" && styles.nav_link_active
                  } text-md text-gray-700 font-bold`}
                >
                  Profile
                </Link>
                <button
                  onClick={() => setLogout(true)}
                  className={`${styles.nav_link} text-md text-gray-700 font-bold`}
                >
                  Logout
                </button>
              </ul>
            ) : (
              <ul className="flex justify-start items-end gap-x-10">
                <button
                  onClick={() => setshowReg(true)}
                  className={`${styles.nav_link} text-md text-gray-700 font-bold`}
                >
                  Signup
                </button>

                <button
                  onClick={() => setshowLogin(true)}
                  className={`${styles.nav_link} text-md text-gray-700 font-bold`}
                >
                  Signin
                </button>
              </ul>
            )}
          </ul>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Navbar;
