"use client";
import ExtraDiv from "@/components/essentials/ExtraDiv";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import React, { useEffect } from "react";

const Transactions = () => {
  const router = useRouter();
  useEffect(() => {
    const token = Cookies.get("accessToken");
    if (!token) {
      router.push("/");
    }
  }, []);
  return (
    <React.Fragment>
      <ExtraDiv data={"Transactions"} />
      <h1>This is transactions page.</h1>
    </React.Fragment>
  );
};

export default Transactions;
