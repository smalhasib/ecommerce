import API from "@/utils/axios";
import React from "react";
import { useForm } from "react-hook-form";
import { MdClear } from "react-icons/md";
import { useMutation, useQueryClient } from "react-query";

type roleArr = {
  id: number;
  role: string;
};
type userType = {
  accountNumber: number;
  email: string;
  id: number;
  name: string;
  username: string;
  isVerified: boolean;
  roles: roleArr[];
};

const EditProfile = ({ editShow, setEditShow, userData }: any) => {
  const form = useForm<userType>({
    defaultValues: {
      id: userData.id,
      name: userData.name,
      username: userData.username,
      accountNumber: userData.accountNumber,
      roles: userData.roles,
      email: userData.email,
      isVerified: userData.verified,
    },
  });
  const { register, handleSubmit, formState } = form;
  const { errors } = formState;
  const queryClient = useQueryClient();

  const { mutate } = useMutation((id, data) => {
    return (
      API.put(`/user/${id}/update`, data),
      {
        onSuccess: () => {
          queryClient.invalidateQueries("single-user");
        },
      }
    );
  });
  const onSubmit = async (data: userType) => {
    mutate(userData.id, data);
    console.log(data);
  };
  return (
    <React.Fragment>
      <div className="min-w-[450px] p-5 rounded-md bg-white flex flex-col items-end">
        <MdClear
          onClick={() => setEditShow(false)}
          className="text-3xl cursor-pointer text-gray-600"
        />
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-full flex flex-col items-center pb-5"
          noValidate
        >
          <h1 className="text-xl font-bold text-black mb-5">
            Edit you profile
          </h1>
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">Name</label>
            <input
              type="text"
              {...register("name", {
                value: register.name,
                required: {
                  value: true,
                  message: "You should fill this field.",
                },
              })}
              className={`w-full px-3 py-2 outline-none border-2 rounded-md mt-1 ${
                errors.username ? "border-red-400" : "focus:border-blue-200"
              }`}
            />
            <p className="text-red-800 ml-3 text-sm mt-1">
              {errors.name?.message}
            </p>
          </div>
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">Username</label>
            <input
              type="text"
              // defaultValue={register.username}
              {...register("username", {
                required: {
                  value: true,
                  message: "You should fill this field.",
                },
              })}
              className={`w-full px-3 py-2 outline-none border-2 rounded-md mt-1 ${
                errors.username ? "border-red-400" : "focus:border-blue-200"
              }`}
            />
            <p className="text-red-800 ml-3 text-sm mt-1">
              {errors.username?.message}
            </p>
          </div>
          <div className="w-full mt-4 flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">
              Account no.
            </label>
            <input
              type="number"
              // defaultValue={register.accountnumber}
              {...register("accountNumber", {
                required: {
                  value: true,
                  message: "You should fill this field.",
                },
              })}
              className={`w-full px-3 py-2 outline-none border-2 rounded-md mt-1 ${
                errors.accountNumber
                  ? "border-red-400"
                  : "focus:border-blue-200"
              }`}
            />
            <p className="text-red-800 ml-3 text-sm mt-1">
              {errors.accountNumber?.message}
            </p>
          </div>

          <button className="group mt-5 relative py-2 px-8 overflow-hidden rounded-lg bg-black text-white font-bold text-md shadow">
            Save
          </button>
        </form>
      </div>
    </React.Fragment>
  );
};

export default EditProfile;
