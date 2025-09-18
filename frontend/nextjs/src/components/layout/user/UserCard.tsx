import styles from './UserCard.module.css'

interface UserCardProps {
    id: number;
    nomeUsuario: string;
    emailUsuario: number;
    cursoUsuario: string;
    classeUsuario: string;
}

function UserCard ({id , nomeUsuario , emailUsuario , cursoUsuario , classeUsuario} : UserCardProps) {

    return (
        <div className={styles.user_card}>
            <h4>{nomeUsuario}</h4>
            <p>
                <span></span> {emailUsuario}
            </p>
            <p>
                <span > {cursoUsuario}</span> 
            </p>
            <p>
                <span > {classeUsuario}</span> 
            </p>

        </div>
    )
}

export default UserCard ;