import API from "@/utils/axios";
import Cookies from "js-cookie";
import React, { useEffect, useState } from "react";

const PendingPayment = () => {
  const [pendingTran, setPendingTran] = useState([]);

  const fetchTransactions = async () => {
    const res = await API.get(
      `purchase/pending-deliveries?sellerId=${Cookies.get(
        "org_user_id"
      )}&pageNumber=0&pageSize=10`
    );
    console.log(res.data.content);
    setPendingTran(res.data.content);
  };

  const confirmPayment = async (id) => {
    await API.get(`/purchase/deliver?purchaseItemId=${id}`);
    fetchTransactions();
  };
  useEffect(() => {
    fetchTransactions();
  }, []);

  return (
    <React.Fragment>
      <div className="w-full min-h-screen flex justify-center">
        <div className="w-[60%] mt-24 flex justify-start text-red ">
          <div className="w-full grid grid-cols-5 gap-10 h-32 items-center">
            {pendingTran
              .filter((tran) => tran.delivered === false)
              .map((pt) => (
                <div
                  key={pt.transactionId}
                  className="shadow-md border-[1px]  flex flex-col p-5 items-center justify-center"
                >
                    <img src={pt.product.imageUrl} alt="phonto" className="h-20 w-20"/>
                  <h1>Price : {pt.product.price}</h1>
                  <button
                    onClick={() => {
                      confirmPayment(pt.purchaseItemId);
                    }}
                    className="px-3 py-1 bg-black text-white mt-3 hover:bg-gray-900 text-md"
                  >
                    Confirm
                  </button>
                </div>
              ))}
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default PendingPayment;
