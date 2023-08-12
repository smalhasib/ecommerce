"use client";
import ExtraDiv from "@/components/essentials/ExtraDiv";
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
      <ExtraDiv data={"Users"} />
      {isLoading ? (
        <Loader />
      ) : (
        <div className="w-full flex justify-center items-center">
          <table className="min-w-max w-[70%] table-auto mt-10">
            <thead>
              <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                <th className="py-3 px-6 text-center">Name</th>
                <th className="py-3 px-6 text-center">Username</th>
                <th className="py-3 px-6 text-center">Money</th>
                <th className="py-3 px-6 text-center">Email</th>
                <th className="py-3 px-6 text-center">Address</th>
              </tr>
            </thead>
            <tbody className="text-gray-600 text-sm font-light">
              {users.map((user) => (
                <tr
                  key={user.id}
                  className="border-b border-gray-200 hover:bg-gray-100"
                >
                  <td className="py-3 px-6 text-center whitespace-nowrap">
                    {user.name}
                  </td>
                  <td className="py-3 px-6 text-center whitespace-nowrap">
                    {user.username}
                  </td>
                  <td className="py-3 px-6 text-center whitespace-nowrap">
                    {user.money} TK
                  </td>
                  <td className="py-3 px-6 text-center whitespace-nowrap">
                    {user.email}
                  </td>
                  <td className="py-3 px-6 text-center whitespace-nowrap">
                    {user.address}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </React.Fragment>
  );
};

export default Users;
