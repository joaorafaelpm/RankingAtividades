"use client";

import { usePathname } from "next/navigation";
import Link from "next/link";
import styles from "./Header.module.css";
import Search from "./Search";

interface HeaderProps {
  onSearchChange: (value: string) => void;
}

export default function Header({ onSearchChange }: HeaderProps) {
  const pathname = usePathname();

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onSearchChange(e.target.value);
  };

  return (
    <div className={styles.container}>
      <ol className={styles.list}>
        <li className={styles.listItem}>
          <Link href="/" className={styles.link}>Home</Link>
        </li>

        {pathname === "/" && (
          <li className={styles.listItem}>
            <Search
              type="text"
              name="search"
              placeholder="Procurar"
              handleOnChange={handleOnChange}
            />
          </li>
        )}

        <li className={styles.listItem}>
          <Link href="/user" className={styles.link}>Cadastrar</Link>
        </li>
      </ol>
    </div>
  );
}
