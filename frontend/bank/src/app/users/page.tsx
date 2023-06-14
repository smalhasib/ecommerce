"use client";
import Loader from "@/components/essentials/Loader";
import { GetAllusers } from "@/redux/features/users/usersApi";
import { fetchUsers } from "@/redux/features/users/usersSlice";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";


const Users = () => {
  const dispatch = useDispatch();
  const router = useRouter();
  const { isLoading, isError, users } = useSelector(
    (state: any) => state.users
  );
  console.log(users);
  useEffect(() => {
    dispatch(fetchUsers());
    const token = Cookies.get("accessToken");
    if (!token) {
      router.push("/");
    }
  }, [dispatch]);
  return (
    <React.Fragment>
      {isLoading ? (
        <Loader />
      ) : (
        <div>
          {users.map((user) => (
            <h1 key={user.id}>{user.name}</h1>
          ))}
        </div>
      )}
    </React.Fragment>
  );
};

export default Users;
