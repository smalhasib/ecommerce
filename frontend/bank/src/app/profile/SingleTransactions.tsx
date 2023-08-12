import API from "@/redux/utils/axios";
import Cookies from "js-cookie";
import React, { useEffect, useState } from "react";

const SingleTransactions = ({id}) => {


  const [transactions, setTransactions] = useState([]);
  const fetchTransactions = async() =>{

    const res = await API.get(`/transaction/${Cookies.get('id')}/user`);
    console.log(res.data)
    setTransactions(res.data.content);
  }

  useEffect(()=>{
    fetchTransactions();
  },[])

  return (
    <React.Fragment>
      <div className="w-full flex justify-center items-center">
        <table className="min-w-max w-[70%] table-auto mt-10">
          <thead>
            <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
              <th className="py-3 px-6 text-center">Seller ID</th>
              <th className="py-3 px-6 text-center">Receiver ID</th>
              <th className="py-3 px-6 text-center">Amount</th>
              <th className="py-3 px-6 text-center">Date</th>
            </tr>


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
          </thead>
        </table>
      </div>
    </React.Fragment>
  );
};

export default SingleTransactions;
