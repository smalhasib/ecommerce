"use client";
import { GetAllusers } from "@/redux/utils/axios";
import React, { useEffect } from "react";

const Users = () => {
  const getUser = async () => {
    await GetAllusers();
  };
  useEffect(() => {
    getUser();
  }, []);
  return <div></div>;
};

export default Users;
