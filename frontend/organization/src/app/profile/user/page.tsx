/* eslint-disable react-hooks/rules-of-hooks */
"use client";
import Details from "@/components/user_profile/Details";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import React, { useEffect } from "react";

const page = () => {
  // const router = useRouter();

  // let role = Cookies.get("user_role");
  // useEffect(() => {
  //   if (role !== "USER") {
  //     router.push("/");
  //   }
  // }, [role, router]);

  return (
    <React.Fragment>
      <div className="w-full flex flex-col items-center min-h-screen mt-28">
        <Details />
      </div>
    </React.Fragment>
  );
};

export default page;
