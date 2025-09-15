"use client";

import { useState } from "react";
import Link from "next/link";
import styles from "./Header.module.css";  // Importando os estilos

import Search from "./Search.js";

export default function Header() {
  const [search, setSearch] = useState("");

  const handleOnChange = (e) => {
    setSearch(e.target.value);
  };

  const pesquisar = () => {
    // fetch(`http://localhost:8080/alunos/${search}` , {
    //   method: "GET" ,
    //   headers : {
    //     "Content-Type" : "application/json" ,
    //   }
    // })
    // .then((response) => response.JSON())
    // .catch((err) => console.log(err + "Aluno não encontrado."));
  };

  return (
    <div className={styles.container}>
      <ol className={styles.list}>
        <li className={styles.listItem}>
          <Link href="/" className={styles.link}>
            Home
          </Link>
        </li>
        <li className={styles.listItem}>
          <Search
            type="text"
            name="search"
            placeholder="Procurar"
            handleOnChange={handleOnChange}
            value={search ? search : ""}
          />
        </li>
        <li className={styles.listItem}>
          <Link href="/user" className={styles.link}>
            Cadastrar
          </Link>
        </li>
      </ol>
    </div>
  );
}
