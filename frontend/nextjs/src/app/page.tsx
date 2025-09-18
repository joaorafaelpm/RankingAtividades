"use client";

import { useEffect, useState } from "react";
import Main from "../components/Main"
import UserCard from "@/components/layout/user/UserCard";

import styles from './page.module.css'

export default function Home() {
  const [alunos, setAlunos] = useState([]);
  const [query, setQuery] = useState("");

  useEffect(() => {
    if (!query) {
      fetch(`http://localhost:8080/alunos`, {
        headers: { 
          "Content-Type": "application/json" 
        }})
        .then((r) => r.json())
        .then((data) => {
          console.log(data)
          setAlunos(data.content)})
        .catch((err) => {
          if (err.name !== "AbortError") console.error(err);
        });
      return;
    }

    // Faço um cooldown de pesquisa de 400ms para evitar chamar a requisição a cada mudança na barra de pesquisa
    // Uso a classe de AbortController para cancelar requisições antigas depois do cooldown de pesquisa
    const controller = new AbortController();
    const timeout = setTimeout(() => {
      fetch(`http://localhost:8080/alunos/by-name/pageable?pageNumber=0&pageSize=200&name=${query}`, {
        headers: { 
          "Content-Type": "application/json" 
        },
        // A gente passa esse sinal assim que faz a requisição e caso dê erro, neste caso, passe os 400ms, a gente retira a requisição, ou seja, nunca vai ter mais de 1 requisição acontecendo
        signal: controller.signal,
      })
        .then((r) => r.json())
        .then((data) => setAlunos(data))
        .catch((err) => {
          if (err.name !== "AbortError") console.error(err);
        });
    }, 400);
    

    // Aqui a gente cancela a requisição e o timeout caso a query mude
    return () => {
      clearTimeout(timeout);
      controller.abort();
    };
  }, [query]);

  return (
    <Main queryChanges={setQuery}>
      <div className={styles.user_list}>
        {alunos.map((aluno: any) => (
          <UserCard 
            id={aluno.id} 
            nomeUsuario={aluno.name} 
            emailUsuario={aluno.email} 
            cursoUsuario={aluno.curso} 
            classeUsuario={aluno.classe} 
            key={aluno.id}
          />
        ))
      }
      </div>
    </Main>
  );
}
