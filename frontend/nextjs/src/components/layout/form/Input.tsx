import { forwardRef } from "react";
import styles from "./Input.module.css";
import { FieldError } from "react-hook-form";

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  error?: FieldError;
}

// forwardRef permite que o react-hook-form registre o input
const Input = forwardRef<HTMLInputElement, InputProps>(
  ({ type = "text", placeholder, error, className = "", ...props }, ref ) => {
    return (
      <div className={styles.form_control}>
        <input
          ref={ref}
          type={type}
          placeholder={placeholder}
          className={`border rounded p-2 w-full ${error ? "border-red-500" : "border-gray-300"} ${className}`}
          {...props}
        />
        {error && <p className="text-red-600 text-sm mt-1">{error.message}</p>}
      </div>
    );
  }
);

Input.displayName = "Input";
export default Input;
