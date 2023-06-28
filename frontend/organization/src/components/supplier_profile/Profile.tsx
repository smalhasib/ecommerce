import API from "@/utils/axios";
import Cookies from "js-cookie";
import React, { useState } from "react";
import { useQuery } from "react-query";
import Loader from "../Loader";
import Modal from "../Modal/Modal";
import Image from "next/image";
import EditProfile from "../user_profile/EditProfile";

const Profile = () => {
  const [editShow, setEditShow] = useState<boolean>(false);
  const id = Cookies.get("org_user_id");

  const { isLoading, data } = useQuery(["single-user", id], () => {
    return API.get(`/user/${id}`);
  });
  if (isLoading) {
    return <Loader />;
  }
  return (
    <React.Fragment>
      {editShow && (
        <Modal>
          <EditProfile
            editShow={editShow}
            setEditShow={setEditShow}
            userData={data?.data}
          />
        </Modal>
      )}
      <div className="w-full flex justify-center">
        <div className="w-[50%] mt-32 flex justify-center ">
          {/* img */}
          <div className="w-full flex flex-col">
            <Image
              src={"/images/default.jpg"}
              height={250}
              width={300}
              alt="default_img"
              className="h-[250px] w-[300px] rounded-md"
            />
            <input
              className="block mt-3 p-2 w-[300px] text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
              id="file_input"
              type="file"
            />
          </div>
          {/* other details */}
          <div className="w-full flex flex-col items-start">
            <h1 className="text-xl font-semibold mt-4">
              Name :
              <span className="ml-3 text-md font-normal text-gray-700">
                {data?.data.name}
              </span>
            </h1>
            <h1 className="text-xl font-semibold mt-4">
              Username :
              <span className="ml-3 text-md font-normal text-gray-700">
                {data?.data.username}
              </span>
            </h1>
            <h1 className="text-xl font-semibold mt-4">
              Email :
              <span className="ml-3 text-md font-normal text-gray-700">
                {data?.data.email}
              </span>
            </h1>
            <h1 className="text-xl font-semibold mt-4">
              Account no. :
              <span className="ml-3 text-md font-normal text-gray-700">
                {data?.data.accountNumber}
              </span>
            </h1>

            <button
              onClick={() => {
                setEditShow(true);
              }}
              className="bg-black text-white text-sm font-bold px-8 py-2 rounded-md mt-5"
            >
              Edit
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Profile;
