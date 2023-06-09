"use client";
import Image from "next/image";
import Link from "next/link";
import React, { useState } from "react";
import navitems from "./navitems";
import styles from "./Navbar.module.css";
import Modal from "../Modal/Modal";
import Login from "../Auth/Login";
import Register from "../Auth/Register";

const Navbar = () => {
  const [showLogin, setshowLogin] = useState<boolean>(false);
  const [showReg, setshowReg] = useState<boolean>(false);
  return (
    <React.Fragment>
      {showLogin && (
        <Modal setshowLogin={setshowLogin}>
          <Login setshowLogin={setshowLogin} setshowReg={setshowReg} />
        </Modal>
      )}

      {showReg && (
        <Modal setshowReg={setshowReg}>
          <Register setshowReg={setshowReg} setshowLogin={setshowLogin} />
        </Modal>
      )}
      <div className="w-full flex justify-center items-center">
        <div className="w-[70%] flex justify-between item-center p-4">
          <div className="flex justify-start items-center gap-x-3">
            <Image
              src={"/icons/logo.png"}
              height={30}
              width={30}
              alt="Logo_of_bank"
            />
            <span className="text-2xl font-bold">Ecommerce-Bank</span>
          </div>
          <ul className="flex justify-start items-end gap-x-8">
            {navitems.map((item) => (
              <li key={item.id}>
                <Link href={`${item.url}`} className={`${styles.nav_link}`}>
                  {item.title}
                </Link>
              </li>
            ))}
            <button
              onClick={() => setshowReg(true)}
              className={`${styles.nav_link}`}
            >
              Register
            </button>
            <button
              onClick={() => setshowLogin(true)}
              className={`${styles.nav_link}`}
            >
              Sigin
            </button>
          </ul>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Navbar;
