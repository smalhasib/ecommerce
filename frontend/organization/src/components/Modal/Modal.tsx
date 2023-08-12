import React from "react";
import styles from "./modal.module.css";
const Modal = ({ children }: any) => {
  return (
    <React.Fragment>
      <div className={styles.modal}>{children}</div>
    </React.Fragment>
  );
};

export default Modal;
