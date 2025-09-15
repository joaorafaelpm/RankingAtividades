import styles from "./Submit.module.css";

export default function SubmitButton({ text }) {
  return (
    <button className={styles.btn}> {text} </button>
  )
}
