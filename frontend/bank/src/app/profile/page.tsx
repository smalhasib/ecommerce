"use client";
import React, { useEffect } from "react";
import ExtraDiv from "@/components/essentials/ExtraDiv";
import SingleTransactions from "./SingleTransactions";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import { useDispatch, useSelector } from "react-redux";
import { fetchUser } from "@/redux/features/user/userSlice";

const Profile = () => {
  const dispatch = useDispatch();
  const user = useSelector((state: any) => state.user);
  console.log(user);
  const router = useRouter();
  useEffect(() => {
    const token = Cookies.get("accessToken");
    if (!token) {
      router.push("/");
    }
  }, [router]);
  useEffect(() => {
    dispatch(fetchUser(Cookies.get("id")));
  }, [dispatch]);
  return (
    <React.Fragment>
      <ExtraDiv data={"Profile"} />
      <div className="w-full flex flex-col items-center">
        <div className="w-[70%] grid grid-cols-2 gap-x-10 mt-10">
          <div className="w-full flex flex-col gap-y-7">
            <div className="flex items-center p-8 bg-white border-2 shadow-md rounded-lg">
              <div className="inline-flex flex-shrink-0 items-center justify-center h-16 w-16 text-gray-600 bg-blue-300 rounded-full mr-6">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M12 6v12m-3-2.818l.879.659c1.171.879 3.07.879 4.242 0 1.172-.879 1.172-2.303 0-3.182C13.536 12.219 12.768 12 12 12c-.725 0-1.45-.22-2.003-.659-1.106-.879-1.106-2.303 0-3.182s2.9-.879 4.006 0l.415.33M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
              </div>
              <div>
                <span className="block text-2xl font-bold">1000 tk</span>
                <span className="block text-gray-500">Money left</span>
              </div>
            </div>
            <div className="flex items-center p-8 bg-white border-2 shadow-md rounded-lg">
              <div className="inline-flex flex-shrink-0 items-center justify-center h-16 w-16 text-green-600 bg-green-100 rounded-full mr-6">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M12 6v12m-3-2.818l.879.659c1.171.879 3.07.879 4.242 0 1.172-.879 1.172-2.303 0-3.182C13.536 12.219 12.768 12 12 12c-.725 0-1.45-.22-2.003-.659-1.106-.879-1.106-2.303 0-3.182s2.9-.879 4.006 0l.415.33M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
              </div>
              <div>
                <span className="block text-2xl font-bold">3</span>
                <span className="block text-gray-500">Total transactions</span>
              </div>
            </div>
          </div>
          <div className="flex flex-col items-start p-8 bg-white border-2 shadow-md rounded-lg">
            <h1 className="text-xl mt-4 text-gray-700">
              <span className="text-xl font-bold mr-3">Name : </span>
              {user.user.name}
            </h1>
            <h1 className="text-xl mt-4 text-gray-700">
              <span className="text-xl font-bold mr-3">Username : </span>
              {user.user.username}
            </h1>
            <h1 className="text-xl mt-4 text-gray-700">
              <span className="text-xl font-bold mr-3">Email : </span>
              {user.user.email}
            </h1>
            <h1 className="text-xl mt-4 text-gray-700">
              <span className="text-xl font-bold mr-3">Account : </span>
              {user.user.accountNumber}
            </h1>
          </div>
        </div>
      </div>
      <SingleTransactions />
    </React.Fragment>
  );
};

export default Profile;
