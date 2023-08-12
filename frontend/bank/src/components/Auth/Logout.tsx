import React from "react";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
const Logout = ({ setLogout }: any) => {
  const router = useRouter();
  const userLogout = (): void => {
    Cookies.remove("accessToken");
    setLogout(false);
    router.push("/");
  };
  return (
    <div className="min-w-[450px] p-5 rounded-md bg-white flex flex-col">
      <h1 className="text-xl text-gray-700">Are you want to log out?</h1>
      <div className="flex gap-x-8 mt-3">
        <button
          onClick={userLogout}
          className="px-3 py-1 hover:text-white hover:bg-[#31ABFC] rounded-md"
        >
          Yes
        </button>
        <button
          onClick={() => setLogout(false)}
          className="px-3 py-1 hover:text-white hover:bg-[#31ABFC] rounded-md"
        >
          No
        </button>
      </div>
    </div>
  );
};

export default Logout;
