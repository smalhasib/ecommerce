"use client";
import ExtraDiv from "@/components/essentials/ExtraDiv";
import API from "@/redux/utils/axios";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";
import { format } from 'date-fns-tz';

const Transactions = () => {
  const router = useRouter();
  const [transactions, setTransactions] = useState([]);
  useEffect(() => {
    const token = Cookies.get("accessToken");
    if (!token) {
      router.push("/");
    }
  }, []);

  const fetchTransactions = async() =>{
    const res = await API.get("/transaction/");
    console.log(res.data.content)
    setTransactions(res.data.content);
  }
  useEffect(()=>{
    fetchTransactions();
  },[])
  return (
    <React.Fragment>
      <ExtraDiv data={"Transactions"} />
      <div className="w-full flex justify-center items-center">
        <table className="min-w-max w-[70%] table-auto mt-10">
          <thead>
            <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
              <th className="py-3 px-6 text-center">Seller ID</th>
              <th className="py-3 px-6 text-center">Receiver ID</th>
              <th className="py-3 px-6 text-center">Amount</th>
              <th className="py-3 px-6 text-center">Date</th>
            </tr>
          </thead>


{
  transactions.map((tns)=>(
    <tr key={tns.id} className="bg-white text-gray-600 text-sm leading-normal border-[1px] border-gray-300">
    <th className="py-3 px-6 text-center">{tns.sender.name}</th>
    <th className="py-3 px-6 text-center">{tns.receiver.name}</th>
    <th className="py-3 px-6 text-center">{tns.amount}</th>
    <th className="py-3 px-6 text-center">{new Date(tns.createdAt).toLocaleDateString()}</th>
  </tr>
  ))
}

         
        </table>
      </div>
    </React.Fragment>
  );
};

export default Transactions;
