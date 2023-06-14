import React, { useState } from "react";
import { userVerify } from "./authApi";

const Otp = ({ setshowLogin, setShowOtp, id }: any) => {
  const [otp, setOtp] = useState<string>();

  const otpSubmit = async () => {
    const res = await userVerify({
      userId: id,
      otp: otp,
    });
    if (res.status == 200) {
      setShowOtp(false);
      setshowLogin(true);
    }
  };
  return (
    <React.Fragment>
      <div className="min-w-[450px] p-4 rounded-md bg-white flex flex-col items-end">
        <div className="w-full flex justify-start">
          <p className="text-md text-gray-700">
            Enter your OTP for verifying your account.
          </p>
        </div>
        <div className="w-full flex flex-col items-start mt-4 focus:border-blue-200">
          <label className="text-sm font-bold text-gray-700">OTP</label>
          <input
            type="text"
            name="otp"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
            className="w-full px-3 py-2 outline-none border-2 rounded-md mt-1"
          />
          <button
            onClick={otpSubmit}
            className="bg-[#31ABFC] py-2 px-4 rounded-md text-white mt-4"
          >
            Submit
          </button>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Otp;
