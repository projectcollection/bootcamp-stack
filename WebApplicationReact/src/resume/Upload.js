import { useState, useRef, useEffect } from "react";
import { getCurrentUser } from "../Utils/ApiUtil";
import { baseurl } from "../include/Urlinclude.js";

const Upload = ({ profile }) => {

    const [profileJson, setProfileJson] = useState();

    useEffect(() => {
        const fetchUser = async () => {
            if (!profile) {
                return await getCurrentUser();
            } else {
                return profile;
            }
        }

        fetchUser().then((data) => {
            setProfileJson(data);
        }).catch((err) => {
            console.log(err)
        });
    }, [])


    const jwtToken = localStorage.getItem("accessToken");
    if (!jwtToken) {
        throw new Error("No access token set.");
    }

    const roles = profileJson?.roles?.map(role => role.role);

    const hasAccess = roles?.some(role => {
        return ["RECRUITER", "SRRECRUITER", "RECRUITERADMIN"].includes(role)
    });

    if (!hasAccess && profileJson) {
        window.location.replace('/profile');
    }

    const dropZone = useRef(null);

    const [filesToUpload, setFiles] = useState([]);
    const [parsedResumes, setParsedResumes] = useState([]);
    const [isUploading, setIsUploading] = useState(false);

    async function uploadFiles() {
        setIsUploading(true);

        const uploadPromises = filesToUpload.map((file) => {
            let formData = new FormData;
            formData.set('file', file, file.name);

            return fetch(`${baseurl}/applicant/uploadFile`, {
                method: 'POST',
                body: formData,
                headers: {
                    Authorization: 'Bearer ' + jwtToken
                }
            });
        });

        const responses = await Promise.all(uploadPromises);
        const resJson = await Promise.all(responses.map(res => res.json()));

        setParsedResumes(resJson);
        setIsUploading(false);
    }

    function handleDrop(event) {
        event.preventDefault();

        let files = [];

        if (event.dataTransfer.items) {
            [...event.dataTransfer.items].forEach((item) => {
                if (item.kind === "file") {
                    const file = item.getAsFile();
                    files.push(file);
                }
            });
        } else {
            [...event.dataTransfer.files].forEach((file) => {
                files.push(file);
            });
        }

        setFiles(files);
    }

    function handleDragOver(event) {
        event.preventDefault();

        const element = dropZone.current;
        element.style.background = "orange";
    }

    function handleDragLeave() {
        const element = dropZone.current;
        element.style.background = "#5780b3";
    }

    if (!hasAccess || !profileJson) {
        return <></>
    }

    if (isUploading) {
        return <>
            <div style={{
                display: 'flex',
                height: '100vh',
                width: '100%',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center'
            }}>
                <h1>
                    Uploading...
                </h1>
            </div>
        </>
    }

    return <>
        <div style={{
            display: 'flex',
            minHeight: '100vh',
            width: '100%',
            flexDirection: 'column',
            justifyContent: 'center',
            alignItems: 'center'
        }}>
            <h1>
                resume upload
            </h1>
            <div>

                <div
                    style={{
                        display: 'flex'
                    }}
                >
                    <div
                        ref={dropZone}
                        onDrop={handleDrop}
                        onDragOver={handleDragOver}
                        onDragLeave={handleDragLeave}
                        style={{
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                            background: '#5780b3',
                            color: 'white',
                            height: '100%',
                            padding: '1rem'
                        }}>
                        <p
                            style={{
                                margin: '0',
                            }}
                        >
                            drag and drop files here
                        </p>
                    </div>
                    {filesToUpload.length > 0 && <button onClick={uploadFiles}>
                        upload
                    </button>
                    }
                </div>
            </div>
        </div>
    </>
}

export default Upload;
