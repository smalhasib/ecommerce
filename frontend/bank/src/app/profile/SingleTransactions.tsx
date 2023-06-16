import React from "react";

const SingleTransactions = () => {
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
          </thead>
        </table>
      </div>
    </React.Fragment>
  );
};

export default SingleTransactions;
