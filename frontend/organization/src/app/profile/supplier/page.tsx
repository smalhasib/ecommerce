"use client";
import AddProducts from "@/components/supplier_profile/AddProducts";
import Profile from "@/components/supplier_profile/Profile";
import Sidebar from "@/components/supplier_profile/Sidebar";
import React, { useState } from "react";

const Supplier = () => {
  const [show, setShow] = useState<string>("dashboard");
  return (
    <React.Fragment>
      <div className="w-full flex justify-start min-h-screen mt-28">
        <Sidebar setShow={setShow} />
        {show === "profile" && <Profile />}
        {show == "addproduct" && <AddProducts />}
      </div>
    </React.Fragment>
  );
};

export default Supplier;
