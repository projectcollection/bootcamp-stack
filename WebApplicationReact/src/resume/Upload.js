import { useState, useRef } from "react";
import { baseurl } from "../include/Urlinclude.js";

const Upload = ({ profile }) => {
    const profileJson = JSON.parse(profile);

    const jwtToken = profileJson.response;
    const roles = profileJson.user.roles.map(role => role.role);

    const hasAccess = roles.some(role => {
        return ["RECRUITER", "SRRECRUITER", "RECRUITERADMIN"].includes(role)
    });

    if (!hasAccess) {
        window.location.replace('/profile');
    }

    const dropZone = useRef(null);

    const [filesToUpload, setFiles] = useState([]);
    const [isUploading, setIsUploading] = useState(false);

    async function uploadFiles() {
        setIsUploading(true);

        const uploadPromises = filesToUpload.map((file) => {
            let formData = new FormData;
            formData.set('file', file, file.name);

            return fetch(`${baseurl}/user/uploadFile`, {
                method: 'POST',
                body: formData,
                headers: {
                    Authorization: 'Bearer ' + jwtToken
                }
            });
        });

        await Promise.all(uploadPromises)

        setIsUploading(false);
    }

    function handleDrop(event) {
        event.preventDefault();

        let files = [];

        if (event.dataTransfer.items) {
            [...event.dataTransfer.items].forEach((item, i) => {
                if (item.kind === "file") {
                    const file = item.getAsFile();
                    files.push(file);
                    console.log(`… file[${i}].name = ${file.name}`);
                }
            });
        } else {
            [...event.dataTransfer.files].forEach((file, i) => {
                files.push(file);
                console.log(`… file[${i}].name = ${file.name}`);
            });
        }

        setFiles(files);
    }

    function handleDragOver(event) {
        event.preventDefault();
    }

    function handleDragEnter(event) {
        const element = dropZone.current;
        element.style.background = "orange";
    }

    function handleDragLeave(event) {
        const element = dropZone.current;
        element.style.background = "blue";
    }

    if (!hasAccess) {
        return <></>
    }

    if (isUploading) {
        return <>
            <h1>
                Uploading...
            </h1>
        </>
    }

    return <>
        <h1>
            resume upload
        </h1>

        <div
            ref={dropZone}
            onDrop={handleDrop}
            onDragOver={handleDragOver}
            onDragEnter={handleDragEnter}
            onDragLeave={handleDragLeave}
            style={{
                background: "blue"
            }}>
            <p>
                drag and drop files here:
            </p>
        </div>

        <button onClick={uploadFiles}>
            upload
        </button>
    </>
}

export default Upload;
