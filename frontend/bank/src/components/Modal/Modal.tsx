import React, { ReactComponentElement } from "react";
import styles from "./Modal.module.css";
const Modal = ({ children }: any) => {
  return <div className={styles.modal}>{children}</div>;
};
export default Modal;
