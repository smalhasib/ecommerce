import React from "react";
import { MdClear } from "react-icons/md";
import { useForm } from "react-hook-form";
import { userLogin } from "./authApi";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
type FormValues = {
  username: string;
  password: string;
};
const Signin = ({ setShowLogin, setShowReg }: any) => {
  const form = useForm<FormValues>();
  const { register, control, handleSubmit, formState } = form;
  const { errors } = formState;
  const router = useRouter();
  const onSubmit = async (data: FormValues) => {
    const res = await userLogin(data);
    console.log(res.data);
    if (res.status == 200) {
      Cookies.set("org_accessToken", res.data.accessToken, { expires: 1 });
      Cookies.set("org_user_id", res.data.userDto.id, { expires: 1 });
      Cookies.set("user_role", res.data.userDto.roles[0].name, { expires: 1 });
      setShowLogin(false);
      console.log(res.data.userDto.roles[0].name);
      if (res.data.userDto.roles[0].name === "USER") {
        router.push("/profile/user");
      } else {
        router.push("/profile/supplier");
      }
      //  save data redux later i will do it....
    } else {
      alert("Something went wrong....");
    }
  };
  return (
    <React.Fragment>
      <div className="min-w-[450px] p-5 rounded-md bg-white flex flex-col items-end">
        <MdClear
          onClick={() => setShowLogin(false)}
          className="text-3xl cursor-pointer text-gray-600"
        />
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-full flex flex-col items-center pb-5"
          noValidate
        >
          <h1 className="text-2xl font-bold text-black mb-5">Signin</h1>
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
              Do not have any account, go for{" "}
              <span
                className="text-black cursor-pointer"
                onClick={() => {
                  setShowReg(true);
                  setShowLogin(false);
                }}
              >
                Signup
              </span>
            </p>
          </div>
          <button className="group mt-5 relative py-2 px-8 overflow-hidden rounded-lg bg-white text-lg shadow">
            <div className="absolute inset-0 w-3 bg-black transition-all duration-[250ms] ease-out group-hover:w-full"></div>
            <span className="relative text-black text-sm font-bold group-hover:text-white">
              Signin
            </span>
          </button>
          <div></div>
        </form>
      </div>
    </React.Fragment>
  );
};

export default Signin;
