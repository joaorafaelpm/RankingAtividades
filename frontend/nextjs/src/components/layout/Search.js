import styles from "./Search.module.css";

export default function Search({ handleOnChange ,placeholder, name , type}) {
  return (
    <label className={styles.form_control}>
      <input
        type={type}
        name={name}
        placeholder={placeholder}
        onChange={handleOnChange}
      />
    </label>
  );
  }
