"use client";
import AddProducts from "@/components/supplier_profile/AddProducts";
import AllProducts from "@/components/supplier_profile/AllProducts";
import Home from "@/components/supplier_profile/Home";
import Profile from "@/components/supplier_profile/Profile";
import Sidebar from "@/components/supplier_profile/Sidebar";
import PendingPayment from "@/components/supplier_profile/PendingPayment";
import React, { useState } from "react";

const Supplier = () => {
  const [show, setShow] = useState<string>("dashboard");

  return (
    <React.Fragment>
      <div className="w-full flex justify-start mt-28">
        <Sidebar setShow={setShow} />
        {show == "dashboard" && <Home />}
        {show === "profile" && <Profile />}
        {show == "addproduct" && <AddProducts setShow={setShow} />}
        {show == "products" && <AllProducts />}
        {show == "pendingpayment" && <PendingPayment/>}
      </div>
    </React.Fragment>
  );
};

export default Supplier;
