import React from "react";
import { MdClear } from "react-icons/md";
import { useForm } from "react-hook-form";
import { userRegister } from "./authApi";

type FormValues = {
  name: string;
  username: string;
  role: string;
  email: string;
  password: string;
};
const Signup = ({ setShowReg, setShowLogin, setId, setShowOtp }: any) => {
  const form = useForm<FormValues>();
  const { register, control, handleSubmit, formState } = form;
  const { errors } = formState;

  const onSubmit = async (data: FormValues) => {
    const res = await userRegister(data);
    if (res.data.userId) {
      setId(res.data.userId);
      setShowOtp(true);
      setShowReg(false);
    } else {
      alert("Something went wrong..");
    }
  };
  return (
    <React.Fragment>
      <div className="min-w-[450px] px-5 py-2 rounded-md bg-white flex flex-col items-end">
        <MdClear
          onClick={() => setShowReg(false)}
          className="text-3xl cursor-pointer text-gray-600"
        />
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-full flex flex-col items-center pb-5"
          noValidate
        >
          <h1 className="text-2xl font-bold text-black mb-5">Signup</h1>
          <div className="w-full flex flex-col items-start">
            <label className="text-sm font-bold text-gray-700">Full name</label>
            <input
              type="text"
              {...register("name", {
                required: {
                  value: true,
                  message: "You should fill this field.",
                },
              })}
              className={`w-full px-3 py-2 outline-none border-2 rounded-md mt-1 ${
                errors.name ? "border-red-400" : "focus:border-blue-200"
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
          <div className="w-full flex flex-col items-start mt-4">
            <span className="text-sm font-bold text-gray-700">Role</span>
            <select
              {...register("role", {
                required: {
                  value: true,
                  message: "You should fill this field.",
                },
              })}
              className="w-full mt-2 bg-white border-2 p-2 px-2 rounded-md text-md focus:border-gray-400 focus:border-2"
            >
              <option value="">Select role</option>
              <option value="USER">BUYER</option>
              <option value="SELLER">SELLER</option>
            </select>
          </div>
          <div className="w-full flex flex-col mt-4 items-start">
            <label className="text-sm font-bold text-gray-700">Email</label>
            <input
              type="text"
              {...register("email", {
                required: {
                  value: true,
                  message: "Email should be provided",
                },
                pattern: {
                  value:
                    /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
                  message: "Invalid email format",
                },
              })}
              className={`w-full px-3 py-2 outline-none border-2 rounded-md mt-1 ${
                errors.email ? "border-red-400" : "focus:border-blue-200"
              }`}
            />
            <p className="text-red-800 ml-3 text-sm mt-1">
              {errors.email?.message}
            </p>
          </div>
          <div className="w-full flex flex-col items-start mt-4 focus:border-blue-200">
            <label className="text-sm font-bold text-gray-700">Password</label>
            <input
              type="password"
              {...register("password", {
                required: {
                  value: true,
                  message: "Password should be provided",
                },
              })}
              className={`w-full px-3 py-2 outline-none border-2 rounded-md mt-1 ${
                errors.password ? "border-red-400" : "focus:border-blue-200"
              }`}
            />
            <p className="text-red-800 ml-3 text-sm mt-1">
              {errors.password?.message}
            </p>
          </div>
          <div className="w-full flex justify-center py-1 mt-3 text-gray-600">
            <p>
              Have already any account, go for
              <span
                onClick={() => {
                  setShowReg(false);
                  setShowLogin(true);
                }}
                className="text-black cursor-pointer"
              >
                Signin
              </span>
            </p>
          </div>
          <button className="group mt-5 relative py-2 px-8 overflow-hidden rounded-lg bg-white text-lg shadow">
            <div className="absolute inset-0 w-3 bg-black transition-all duration-[250ms] ease-out group-hover:w-full"></div>
            <span className="relative text-black text-sm font-bold group-hover:text-white">
              Signup
            </span>
          </button>
          <div></div>
        </form>
      </div>
    </React.Fragment>
  );
};

export default Signup;
